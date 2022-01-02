<template>
    <v-app>
        <div id='login_container'>
            <div id='main_section'>
                
                <div class = 'login_textfields'>
                    <div class = 'textfield_title'>별명 (최대 6자)</div>
                    <v-text-field
                        filled
                        outlined
                        background-color="white"
                    ></v-text-field>

                    <div class = 'textfield_title'>출생연도</div>

                    <div class = 'field_section'>
                        <v-select
                            :items="items"
                            outlined
                            hint="출생연도"
                            background-color="white"
                        ></v-select>
                        
                        <span style="width:10%;"></span>

                        <v-btn-toggle 
                        v-model="toggle_exclusive"
                        background-color="#0473e1"
                        >
                            <v-btn>
                                <span>남자</span>
                            </v-btn>

                            <v-btn>
                                <span>여자</span>
                            </v-btn>
                        </v-btn-toggle>
                        
                    </div>
                    
                    

                    <div class = 'textfield_title'>본의명의 휴대폰번호</div>

                    <div class="field_section">
                        <v-text-field
                            filled
                            outlined
                            background-color="white"
                            v-model="phone_num"
                        ></v-text-field>

                        <span style="width:10%;"></span>

                        <v-btn
                            id = "validate_btn"
                            rounded
                            color="white"
                            v-on:click="login"
                        >
                            인증하기
                        </v-btn>
                    </div>

                    

                    <div class = 'textfield_title'>인증번호 6자리</div>
                    <v-text-field
                        filled
                        outlined
                        background-color="white"
                        v-model="check_number"
                    ></v-text-field>

                </div>
            </div>

            <div id = 'blank_section'>
                <v-btn
                    rounded
                    color="white"
                    v-on:click="check_login"
                >
                    위캣 시작하기
                </v-btn>
            </div>
            
            <div id = 'bottom_section'>해당 정보는 연령별, 성별, 맞춤식 피싱 예방을 위해 필수정보만 수집합니다.</div>

            <!-- <div id='login_button' v-on:click='login'>
                
            </div> -->
            
        </div>

    </v-app>
</template>

<script>
    import firebase from 'firebase/compat/app';
    import * as firebaseui from 'firebaseui'
    import 'firebaseui/dist/firebaseui.css'
    import { initializeApp } from "firebase/app";
    import { getAuth, RecaptchaVerifier, signInWithPhoneNumber } from "firebase/auth";

    import 'firebase/auth';
    import { getFirestore, collection, getDocs, doc, setDoc,getDoc  } from 'firebase/firestore/lite';


    
    export default {
        data: function(){
            return{
                phone_num: '',
                check_number: '',
                items: [
                    '2021', '2020', '2019', '2018',
                    '2017', '2016', '2015', '2014',
                    '2013', '2012', '2011', '2010',
                    '2009', '2008', '2007', '2006',
                    '2005', '2004', '2003', '2002',
                    '2001', '2000', '1999', '1998',
                    '1997', '1996', '1995', '1994',
                ],
            }
	    },
        
        methods: {
            login(){
                // console.log(this.phone_num);
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
                const auth = getAuth(app);
                const phoneNumber = this.phone_num;

                this.recaptchaVerifier = new RecaptchaVerifier('validate_btn', {
                    'size': 'invisible',
                    'callback': (response) => {
                        // reCAPTCHA solved, allow signInWithPhoneNumber.
                        onSignInSubmit();
                    }
                }, auth);
                
                const appVerifier = this.recaptchaVerifier;

                signInWithPhoneNumber(auth, phoneNumber, appVerifier)
                    .then((confirmationResult) => {
                        // SMS sent. Prompt user to type the code from the message, then sign the
                        // user in with confirmationResult.confirm(code).
                        window.confirmationResult = confirmationResult;
                        console.log('yeap');
                        // ...
                    }).catch((error) => {
                       
                    // Error; SMS not sent
                    // ...
                        console.log(error);
                        
                    });
                
                

            },

            async check_login(){
                const code = this.check_number;
                confirmationResult.confirm(code).then((result) => {
                // User signed in successfully.
                const user = result.user;
                console.log('what user is? :: '+user);


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
                const db = getFirestore(app);

                console.log('login success!!');
                setDoc(doc(db, this.phone_num, this.phone_num), {
                    name: this.phone_num,
                });
                this.cordovaUidSave(this.phone_num);
                this.$router.push('/home');
                // ...
                }).catch((error) => {
                    console.log('login fail..')
                    console.log(error)
                // User couldn't sign in (bad verification code?)
                // ...
                });
            },
            async cordovaUidSave(uid){
                return new Promise(function(resolve, reject){
                    cordova.exec(UidSave, null,"CordovaCustomAuthPlugin", "CordovaUidSave", [uid]);
                    resolve();
                });
            }, 
        
        }
    }

function UidSave(result){

}
</script>

<style scoped>
    #login_start_button{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        width: 30%;
        background-color: white;
        color: black;
        border-radius: 15px;
    }
    .field_section{
        display: flex;
        flex-direction: row;
    }
    .textfield_title{
        color: white;
    }
    .login_textfields{
        width: 80%;
    }
    /* .textfield{
        background-color: white;
    }
     */
    #main_section{
        flex: 1 1 0;
        width: 100vw;
        
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
    #blank_section{
        flex: 0.3 1 0;
        width: 100vw;

        
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }
    #bottom_section{
        flex: 0.1 1 0;
        width: 100vw;
        font-size: 1px;
        color: white;

        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    #login_container{
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

        background-color: #0473e1;
        height: 100%;
        width: 100vw;
    }
    #login_button{
        background-color: azure;
        width: 30%;
    }
    #login_button:active{
        background-color: aqua;
    }
</style>