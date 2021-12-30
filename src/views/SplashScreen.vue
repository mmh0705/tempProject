<template>
    <div class='splash_container'>
        <div class='main_space_one'></div>
        <!-- <div class='main_titleMent'></div> -->

        <div class='main_titleImg'>
            <img src="assets/splash_subtitle.png" id="splash_subtitle"/>
            <img src="assets/we_ka_t_start.png" id="startImg"/>
        </div>

        <div class='main_btnSection'>
            <div class='main_btnSection_btn' v-on:click="goLogin">
                <div id=main_btnSection_btn_text><b>시작하기</b></div>
            </div>
        </div>

        <div id='addFirestore' v-on:click="test">
            dddd
        </div>

        <div class='main_space_two'></div>
        <div class='main_permission'>시작하기를 누르면 이용약관 및 정책 동의로 간주합니다</div>
        
    </div>
</template>

<script>
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getFirestore, collection, getDocs, doc, setDoc } from 'firebase/firestore/lite';

const firebaseConfig = {
  apiKey: "AIzaSyCUNwu_eqDg7SSNv2qx6Obl0FbAi5_bokE",
  authDomain: "vue-js-wekat.firebaseapp.com",
  projectId: "vue-js-wekat",
  storageBucket: "vue-js-wekat.appspot.com",
  messagingSenderId: "774271357841",
  appId: "1:774271357841:web:b18c6c8e5f8826c6d4c030",
  measurementId: "G-NQ5JQL7L2Y"
};

const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);
const db = getFirestore(app);



// To apply the default browser preference instead of explicitly setting it.
// firebase.auth().useDeviceLanguage();
function happy(){
    setDoc(doc(db, "cities", "LA"), {
        name: "Los Angeles",
        state: "CA",
        country: "USA"
    });
}

export default {
    data: function(){
        return{

        }
	},
	methods: {
        goLogin(){
            this.$router.push('/login')
        },
		goHome(){
            this.$router.push('/home')
		},
        async test(){
            await setDoc(doc(db, "cities", "LA"), {
                name: "Los Angeles",
                state: "CA",
                country: "USA"
            });
        }
	}
}
</script>

<style scoped>
@keyframes fadeOut {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

#addFirestore{
    background-color: aliceblue;
    width: 50%;
}

#main_btnSection_btn_text{
    animation-name: fadeOut;
    animation-duration: 0.8s;
    animation-delay: 0s;
    animation-iteration-count: infinite;
    animation-timing-function: linear;
    animation-direction: alternate;
}

#splash_subtitle{
    width: 49%;
}

#startImg{
    width: 50%;
}

div {
  margin: 0;
  padding: 0;
}

.splash_container{
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 100vw;
    background-color: #0473e1;

    animation-name: fadeIn;
    animation-duration: 0.2s;
    animation-delay: 0s;
    /* animation-iteration-count: infinite; */
    animation-timing-function: linear;
    animation-direction: alternate;
}


.main_space_one{
    flex: 2 1 0;
    background-color: #0473e1;
}

.main_titleMent{
    flex: 0.3 1 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    font-size: 130%;
    color: white;
    background-color:#0473e1;
}

.main_titleImg{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    color: white;
    background-color:#0473e1;
}

.main_btnSection{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    background-color:#0473e1;
}
.main_btnSection_btn{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    height: 40%;
    width:50%;
    border-radius: 30px;
    color: #0473e1;
    background-color:white;

    
}



.main_space_two{
    flex: 3 1 0;
    background-color: #0473e1;
}
.main_permission{
    flex: 1 1 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    color: white;

    font-size: 70%;

    background-color:#0473e1;
}

/* .logo_img_section{
    flex: 7 1 0;
    background-color:#0473e1;
}
.button_section{
    display: flex;
    flex: 1 1 0;
    background-color:brown;
    align-items: center;
    justify-content: center;
}
.start_button{
    display: flex;
    align-items: center;
    justify-content: center;
  
    height: 40%;
    width: 80%;
    
    border-radius: 8px;
    background-color:blue;
    color: white;
} */
</style>