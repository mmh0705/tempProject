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

                    <div id = 'select_section'>
                        <v-select
                            :items="items"
                            outlined
                            hint="출생연도"
                            background-color="white"
                        ></v-select>

                        
                    </div>
                    
                    

                    <div class = 'textfield_title'>본의명의 휴대폰번호</div>
                    <div class = 'textfield_title'>인증번호 6자리</div>

                </div>
            </div>

            <div id = 'blank_section'>d</div>
            
            <div id = 'bottom_section'>d</div>

            <div id='login_button' v-on:click='login'>
                
            </div>
            
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


    
    export default {
        data: function(){
            return{
                items: [
                    '2021', '2020', '2019', '2018',
                    '2017', '2016', '2015', '2014',
                    '2013', '2012', '2011', '2010',
                    '2009', '2008', '2007', '2006',
                    '2005', '2004', '2003', '2002',
                ],
            }
	    },
        
        methods: {
            login(){
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
                const phoneNumber = '+2-10-5115-8604';

                this.recaptchaVerifier = new RecaptchaVerifier('login_button', {
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
                        // grecaptcha.reset(window.recaptchaWidgetId);

                        // // Or, if you haven't stored the widget ID:
                        // window.recaptchaVerifier.render().then(function(widgetId) {
                        //     grecaptcha.reset(widgetId);
                        // });
                    });
                
                const code = getCodeFromUserInput();
                confirmationResult.confirm(code).then((result) => {
                // User signed in successfully.
                const user = result.user;
                // ...
                }).catch((error) => {
                // User couldn't sign in (bad verification code?)
                // ...
                });

            },
        
        }
    }
</script>

<style scoped>
    #select_section{
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
        flex: 0.5 1 0;
        width: 100vw;
        background-color: blueviolet;
    }
    #bottom_section{
        flex: 0.1 1 0;
        width: 100vw;
        background-color: brown;
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