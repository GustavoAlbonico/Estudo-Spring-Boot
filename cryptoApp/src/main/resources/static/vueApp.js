const { createApp } = Vue

const mainConteiner = {
    data() {
        return {
            coins:[]
        }
    },
    mounted(){
        this.coins = [
            {
                name: 'BITCOIN',
                quantity: 0.003
            },
            {
                name: 'TESTE',
                quantity: 0.004
            }
       ]
    }
}

createApp(mainConteiner).mount('#app')