const { createApp } = Vue

const baseUrl = "http://localhost:8080/coin/"

const mainConteiner = {
    data() {
        return {
            coins:[],
            canSeeTransactions: false,
            formCoin: {
                isNew: true,
                name: '',
                price: '',
                quantity: '',
                title: 'Cadastrar nova transação',
                button: 'Cadastrar'
            },
            transactions: []
        }
    },
    mounted(){
        this.showAllCoins()
    },
    methods:{
         showAllCoins(){
             axios
                  .get(baseUrl)
                  .then(response => {
                      response.data.forEach(item => {
                          this.coins.push(item)
                      })
                  })
       },
       showTransactions(name){
               this.transactions = {
                    coinName: name,
                    data: []

               }

               this.canSeeTransactions = true

               axios.get(baseUrl + name)
                    .then(response => {
                        response.data.forEach(item => {
                            this.transactions.data.push({
                                id: item.id,
                                name: item.name,
                                price: item.price.toLocaleString('pt-br', { style: 'currency', currency: 'BRL'}),
                                quantity: item.quantity,
                                datetime: this.formattedDate(item.dateTime)
                            })
                        })
                    })
                    .catch(function (error){
                     toastr.error('Não foi possivel visualizar as transações dessa moeda', 'Transações')
                    })
       },
       saveCoin(){

            this.formCoin.name = this.formCoin.name.toUpperCase()
            this.formCoin.price = this.formCoin.price.replace("R$",'')
            .replace(',','.').trim()

            if(this.formCoin.name == '' || this.formCoin.price == '' || this.formCoin.quantity == ''){
                toastr.error('Todos os campos são obrigatórios', 'Formulário')
                return
            }

            const self = this

            if(this.formCoin.isNew){
                const coin = {
                name: this.formCoin.name,
                price: this.formCoin.price,
                quantity: this.formCoin.quantity
                }

                axios.post(baseUrl,coin)
                .then(function (response){
                    toastr.success('Nova transação cadastrada com sucesso!', 'Formulário')
                })
                .catch(function (error){
                    toastr.error('Não foi possivel cadastrar uma nova transação', 'Formulário')
                })
                .then(function (){
                    self.coins = []
                    self.showAllCoins()
                    self.showTransactions(self.formCoin.name)
                    self.cleanForm()
                })
            } else {
                const coin = {
                id: this.formCoin.id,
                name: this.formCoin.name,
                price: this.formCoin.price,
                quantity: this.formCoin.quantity
                }

                axios.put(baseUrl,coin)
                 .then(function (response){
                    toastr.success('Transação atualizada com sucesso!', 'Formulário')
                 })
                 .catch(function (error){
                    toastr.error('Não foi possivel atualizar a transação', 'Formulário')
                 })
                 .then(function (){
                    self.coins = []
                    self.showAllCoins()
                    self.showTransactions(self.formCoin.name)
                    self.cleanForm()
                 })
            }
       },
         removeTrasaction(transaction){
            const self = this

            axios.delete(baseUrl + transaction.id)
            .then(function (response){
                toastr.success('Transação removida com sucesso!', 'Exclusão')
            })
            .catch(function(error){
                toastr.error('Não foi possivel remover a transação!', 'Exclusão')
            })
            .then(function(){
                self.coins = []
                self.showAllCoins()
                self.showTransactions(transaction.name)
                self.cleanForm()
            })
       },
       editTrasaction(transaction){
            this.formCoin = {
                isNew: false,
                id: transaction.id,
                name: transaction.name.toUpperCase(),
                price: transaction.price,
                quantity: transaction.quantity,
                title: 'Editar transação',
                button: 'Atualizar'
            }
       },
       cleanForm(){
            this.formCoin.isNew = true
            this.formCoin.name = ''
            this.formCoin.price = ''
            this.formCoin.quantity = ''
            this.formCoin.title = 'Cadastrar nova transação'
            this.formCoin.button = 'Cadastrar'
       },
       formattedDate(date){
       return ((new Date(date)).toLocaleString('pr-br')).split(',')[0]
       }
    }
}

createApp(mainConteiner).mount('#app')