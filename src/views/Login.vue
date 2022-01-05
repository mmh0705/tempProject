<template>
    <v-app>
        <div id='login_container'>
            <div id='main_section'>
                
                <div class = 'login_textfields'>
                    <v-form
                        ref="form"
                        v-model="valid"
                        lazy-validation
                    >
                    <div class = 'textfield_title'>별명 (최대 6자)</div>
                        <v-text-field
                            v-model="nick_name"
                            filled
                            outlined
                            background-color="white"

                            :rules="[rules.nickName_required, rules.counter]"
                            required
                            
                        >
                            <!-- <v-icon v-if="" slot="append" color="green darken-2">
                                check
                            </v-icon> -->
                        </v-text-field>

                        <div class = 'textfield_title'>출생연도</div>

                        
                       
                        <div class = 'field_section'>
                            <v-select
                                :items="items"
                                v-model="birth_year"
                                outlined
                                label="출생연도"
                                solo
                                color="grey"
                                background-color="white"

                                :rules="[v => !!v || '출생연도를 골라주세요!']"
                                required
                            ></v-select>
                            
                            <span style="width:10%;"></span>

                            <!-- <span>
                                남자
                            </span>
                            <span>
                                여자
                            </span> -->
                            <v-btn-toggle 
                                v-model="toggle_exclusive"
                                background-color="#0473e1"
                            >
                                <v-btn v-on:click="gender='남자'">
                                    <span>남자</span>
                                </v-btn>

                                <v-btn v-on:click="gender='여자'">
                                    <span>여자</span>
                                </v-btn>
                            </v-btn-toggle>
                        </div>
                        
                        

                        <div class = 'textfield_title'>본인명의 휴대폰번호</div>

                        <div class="field_section">
                            <v-text-field
                                filled
                                outlined
                                solo
                                background-color="white"
                                v-model="phone_num"

                                :rules="[rules.phoneNum_required]"
                                required

                                prefix="+82"
                                oninput="javascript: this.value = this.value
                                    .replace(/[^0-9]/, '')
                                    .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);"
                            ></v-text-field>

                            <span style="width:15%;"></span>

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
                    </v-form>
                    

                </div>
            </div>

            <div id = 'blank_section'>
                <v-btn
                    rounded
                    color="white"

                    :disabled="!valid"
                    @click="validate"
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

    // const autoHyphen = (target) => {
    //     target.value = target.value
    //     .replace(/[^0-9]/, '')
    //     .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
    // }
    
    export default {
        data: function(){
            return{
                nick_name: '',
                birth_year:'',
                gender: '',
                valid: false,
                
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
                
                rules: {
                    nickName_required: value => !!value || '별명을 입력해주세요!',
                    phoneNum_required: value => !!value || '전화 번호를 입력해주세요!',
                    counter: value => value.length <= 6 || '6자 이하로 입력해주세요!',
                    email: value => {
                        const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                        return pattern.test(value) || 'Invalid e-mail.'
                    },
                },
            }
	    },
        
        methods: {
            validate () {
                if(this.$refs.form.validate() == true){
                    console.log(this.nick_name);
                    console.log(this.birth_year);
                    console.log(this.gender);
                    console.log(this.phone_num);

                    this.check_login();
                }else{

                }
            },
            goHome(){
                this.$router.push('/home');
                console.log(this.phoneFomatter(this.phone_num));
            },
            login(){
                // console.log(this.phone_num);
                var temp_phone_num = '+82-'+this.phone_num.substr(1);
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
                const phoneNumber = temp_phone_num;

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
                
                var date = new Date();
                var today = date.getFullYear()+'-'
                +(date.getMonth()+1)+'-'
                +date.getDate()+' '
                +date.getHours()+':'
                +date.getMinutes();           
                
                console.log('login success!!');
                setDoc(doc(db, 'users', this.phone_num), {
                    nickname: this.nick_name,
                    birth: this.birth_year,
                    gender: this.gender,
                    hashedphone: this.phone_num,
                    registered_at: today,
                    updated_at: today
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
        /* background-color: brown; */
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