const { createApp } = Vue

createApp({
    data() {
        return {
            isPopupOpen: false,
            formData: {
                name: "",
                email: ""
            }
        }
    },
    methods: {
        openPopup() {
          this.isPopupOpen = true;
        },
        closePopup() {
          this.isPopupOpen = false; // ポップアップを非表示
        },
        submitForm() {
          axios.post("http://your-java-server-url", this.formData)
            .then(response => {
              console.log("データが送信されました。");
              // 必要な処理を追加
            })
            .catch(error => {
              console.error(error);
              // エラーハンドリングを行う必要がある場合は追加
            });
    
          this.isPopupOpen = false; // フォーム送信後にポップアップを閉じる
        }
      }
}).mount('#app')
