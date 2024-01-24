const{ createApp } = Vue

    const baseUrl = "http://localhost:8080/user"

    const mainContainer = {
            data(){
                return{
                    
                }
            },
            mounted() {
                axios.get(baseUrl, {
                    auth: {
                            username: "admin",
                            password: "123"
                    }
                }).then(response => {
                    response.data.forEach(element => {
                        console.log(element)
                    });
                })
            },
        }

        createApp(mainContainer).mount('#app')
