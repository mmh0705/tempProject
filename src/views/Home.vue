<template>
 <v-app>
  <div class="home_container">

    <div 
      style="position:absolute; top: 45%; left: 45%;" 
      v-if="isLoading==true">
      <v-progress-circular
        :size="50"
        color="primary"
        indeterminate
      ></v-progress-circular>
    </div>
    
    <div id="home_appBar">
      <div id="logo">
        <img src="assets/we_ka_t.png" id="logo_img" />
      </div>
      <div id="empty_box"></div>
     
      <div id="dots">
          <v-icon v-on:click="drawer = !drawer">more_vert</v-icon>
      </div>
      <v-navigation-drawer v-model="drawer"  
      absolute
      temporary 
      right>
        <div v-on:click='secretDeveloperMenu' style="margin-left: 10px; margin-top: 10px; background-color:#9fa0a0;" >
          개발자 문자 분석
        </div>
      </v-navigation-drawer>
    </div>
 
   
    

    <div id="home_cat">
      <div :class="{
                  'background_question':level==0,
                  'background_fish': level==4 , 
                  'background_rain': level==3,
                  'background_caution' :level==2,
                  'background_warning': level==1,
                  }">
          <img v-if="level==4" src="assets/cat.svg" id="catFace_img" />
          <img v-if="level==3" src="assets/cat_gloomy.svg" id="catFace_img" />
          <img v-if="level==2" src="assets/cat_angry.svg" id="catFace_img" />
          <img v-if="level==1" src="assets/cat_dead.svg" id="catFace_img" />
          <img v-if="level == 0" src="assets/cat_zero.svg" id="catFace_img" />
		  <!-- <div id='background_head'>
			 
		  </div> -->
      </div>
    </div>


    <div id="home_score_column">
        <div id="home_score_row">
            <div :class="{'score_current_green':level==4,
              'score_current_yellow':level==3,
              'score_current_orange':level==2,
              'score_current_red': level==1,
              'score_current_blue': level==0,
              }">
              <span v-if="level == 0" style="margin-right: 10%;">-</span>
              <span v-if="level != 0">{{score_number}}</span>
            </div>
            
            <div id="score_total">/100</div>
        </div>
        <div  v-if="level==4" id='home_score_ment'>
            완벽한 방어중입니다.
        </div>
        <div  v-if="level==3" id='home_score_ment1'>
            관리가 필요합니다.
        </div>
        <div  v-if="level==2" id='home_score_ment2'>
            주의가 필요합니다.
        </div>
        <div  v-if="level==1" id='home_score_ment3'>
            피싱에 취약합니다.
        </div>
        <div  v-if="level==0" id='home_score_ment3'>
            
        </div>
    </div>

	<div id='detail_button'> 
        <div id='detail_button_btn' v-on:click='phishingAnalysis'><img src="assets/analysis_button_text.png" style="width: 45%;"></div>
        
        <!-- #############피싱 분석 페이지 시작점 ###################-->
        <v-bottom-sheet
          class="overflow-y-auto"
          v-model="GahyeonSmsDetailPageToggle"
          fullscreen
          overlay-color="white"
          max-height="auto"
          scrollable
        >
          <v-sheet class="overflow-y-auto" style="text-align: center">
            <!-- 피싱 분석 페이지 시작점 -->
            <div class="analysis_top">
              <b-icon-x
                id="x_button"
                @click="GahyeonSmsDetailPageToggle = !GahyeonSmsDetailPageToggle"
              />
              <div id="analysis_title">
                <img src="assets/we_ka_t.png" id="anlysis_title_img" />
              </div>
              <div id="empty_box_2"></div>
            </div>
            <div id="analysis_type">피싱 분석</div>
            <div id="analysis_safety_score">문자 안심점수</div>
            <br />

            <div id="home_score_column">
              <div id="home_score_row">
                <div :class="{
                  'score_current_green': level==4,
                  'score_current_yellow': level==3,
                  'score_current_orange': level==2,
                  'score_current_red': level==1,
                  }">

                  <number
                  ref="number1"
                  :from="0"
                  :to="score_number"
                  :duration="1"
                    :delay="0"
                    easing="Power2.easeOut"/>

                </div>

                <div id="score_total">/100</div>
              </div>
            </div>

              <br/>

              <div
                style="
                  font-size: 20px;
                  font-family: 'Gowun Dodum', sans-serif;
                  text-align: center;
                  color: grey;
                "
              >
                <div>
                  홍길동님의 보안지수는
                  <span :class="{ 
                    'level_green':level==4,
                  'level_yellow':level==3,
                  'level_orange':level==2,
                  'level_red':level==1,}">Lv.{{level}}</span>입니다.
                  <!--Level should be changed by score.-->
                </div>


                <div v-if="level==4">
                지금처럼 보안습관을 유지해주세요.
                </div>
                <div v-if="level==3">
                    피싱 분석을 통한 관리가 필요합니다.
                </div>
                <div v-if="level==2">
                    피싱 분석을 통한 주의가 필요합니다.
                </div>
                <div v-if="level==1">
                    지금 바로 피싱 분석이 필요합니다.
                </div>


                <div >
                <!--Comments should be changed by score.-->
              </div>
            </div>
          </v-sheet>
        </v-bottom-sheet>
  
	</div>

	<div id='detail_text'>
        <div id='detail_text_text'> 
            <div v-if="level!=0">
                홍길동님의 보안지수는<span 
                :class="{
                  'level_green':level==4,
                  'level_yellow':level==3,
                  'level_orange':level==2,
                  'level_red':level==1,
                }">Lv.{{level}}</span>입니다.
            </div> 
            <div v-if="level==4">
                지금처럼 보안습관을 유지해주세요.
            </div>
            <div v-if="level==3">
                피싱 분석을 통한 관리가 필요합니다.
            </div>
            <div v-if="level==2">
                피싱 분석을 통한 주의가 필요합니다.
            </div>
            <div v-if="level==1">
                지금 바로 피싱 분석이 필요합니다.
            </div>
            
            <div v-if="level==0">피싱분석을 눌러</div>
            <div v-if="level==0">홍길동님의 보안지수를 확인해주세요</div>
        </div>
	</div>

	<div id='detail_score'> 
		<div class='detail_score_section'>
            <img src='assets/type.svg' v-on:click="GahyeonType=!GahyeonType">
            <!-- <div id='sms_number'>{{susinSMS}}</div>
            <div id='detail_score_text'>수신문자</div> -->
        </div>
		<div class='detail_score_section'>
            <img src='assets/time.svg' v-on:click='GahyeonTime=!GahyeonTime'>
            <!-- <div id='question_number'>{{nowaySMS}}</div>
            <div id='detail_score_text'>알 수 없는 문자</div> -->
        </div>
		<div class='detail_score_section'>
            <img src='assets/sms_icon.svg' v-on:click='GahyeonSms=!GahyeonSms'>
            <div id='detail_score_text'>문자</div>
            
            <div id='exclamation_number'>
              <span style="color: #009944; font-size:10px;">{{fullSMS}}</span>
              <span class='slash_between'>/</span> 
              <span style="color: #ffcc00; font-size:10px;">{{notInSMS}}</span> 
              <span class='slash_between'>/</span>  
              <span style="color: #e60012; font-size:10px;">{{adSMS}}</span>
            </div>
            
        </div>
        <div class='detail_score_section'>
            <img src='assets/word.svg' v-on:click='GahyeonWord=!GahyeonWord'>
            <!-- <div id='question_number'>{{nowaySMS}}</div>
            <div id='detail_score_text'>알 수 없는 문자</div> -->
        </div>


        <!--#########################유형!!!!!!!#######################--->
          <v-bottom-sheet
            class="overflow-y-auto"
            v-model="GahyeonType"
            fullscreen
            overlay-color="white"
            max-height="auto"
            scrollable
          >
            <v-sheet class="overflow-y-auto" style="text-align: center">

              <div class="analysis_top">
                <b-icon-x id="x_button" @click="GahyeonType = !GahyeonType" />
                <div id="analysis_title">
                  <img src="assets/we_ka_t.png" id="anlysis_title_img" />
                </div>
                <div id="empty_box_2"></div>
              </div>
              <div id="analysis_type">유형</div>
              <br/>
              <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%"
                >
                  <swiper-slide>2021-11-01 ~ 2021-11-08</swiper-slide>
                  <swiper-slide>2021-11-09 ~ 2021-11-17</swiper-slide>
                  <swiper-slide>2021-11-18 ~ 2021-11-25</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>
              <div id="analysis_safety_score">
                  가장 많이 받은 유형
              </div>
              
              <template>
                <vc-donut
                  :sections="sortedArrayTypeSection"
                  :thickness="50"
                  :has-legend="true"
                  style="
                    font-size: 10px;
                    font-family: 'Gowun Dodum', sans-serif;
                  "
                >
                  <b>미등록된</b> <br> 
                  <b>번호</b>
                </vc-donut>
              </template>
              <div id='typeSection_list'>
                
                <div class = 'typeSection_list_component'>
                  <div class = 'typeSection_list_component_left'>
                    <div :class="{
                      'typeNumberCSS_URL': sortedArrayRankedList[0].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[0].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[0].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[0].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[0].title == '[국외발신]' ,
                    }"> 
                      1. <b>{{sortedArrayRankedList[0].title}}</b>
                    </div>
                    <!--sex-->
                    <div :class="{
                      'typeNumberCSS_URL': sortedArrayRankedList[0].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[0].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[0].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[0].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[0].title == '[국외발신]' ,
                    }"> 
                      {{sortedArrayRankedList[0].number}} messages
                    </div>
                  </div>
                  <div class = 'typeSection_list_component_blank'>
                       
                  </div>
                  <div class = 'typeSection_list_component_right'>8일 전</div>
                </div>

                <div class = 'typeSection_list_component'>
                  <div class = 'typeSection_list_component_left'>
                    <div :class="{
                      'typeNumberCSS_URL': sortedArrayRankedList[1].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[1].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[1].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[1].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[1].title == '[국외발신]' ,
                    }"> 
                      2. <b>{{sortedArrayRankedList[1].title}}</b>
                    </div>
                    <div :class="{
                      'typeNumberCSS_URL':sortedArrayRankedList[1].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[1].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[1].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[1].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[1].title == '[국외발신]' ,
                    }"> 
                      {{sortedArrayRankedList[1].number}} messages
                    </div>
                  </div>
                  <div class = 'typeSection_list_component_blank'></div>
                  <div class = 'typeSection_list_component_right'>3일 전</div>
                </div>

                <div class = 'typeSection_list_component'>
                  <div class = 'typeSection_list_component_left'>
                    <div :class="{
                      'typeNumberCSS_URL': sortedArrayRankedList[2].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[2].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[2].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[2].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[2].title == '[국외발신]' ,
                    }"> 
                      3. <b>{{this.sortedArrayRankedList[2].title}}</b>
                    </div>
                    <div :class="{
                      'typeNumberCSS_URL':sortedArrayRankedList[2].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[2].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[2].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[2].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[2].title == '[국외발신]' ,
                    }"> 
                      {{this.sortedArrayRankedList[2].number}} messages
                    </div>
                  </div>
                  <div class = 'typeSection_list_component_blank'></div>
                  <div class = 'typeSection_list_component_right'>14일 전</div>
                </div>

                <div class = 'typeSection_list_component'>
                   <div class = 'typeSection_list_component_left'>
                    <div :class="{
                      'typeNumberCSS_URL': sortedArrayRankedList[3].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[3].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[3].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[3].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[3].title == '[국외발신]' ,
                    }"> 
                      4. <b>{{this.sortedArrayRankedList[3].title}}</b>
                    </div>
                    <div :class="{
                      'typeNumberCSS_URL':sortedArrayRankedList[3].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[3].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[3].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[3].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[3].title == '[국외발신]' ,
                    }"> 
                      {{this.sortedArrayRankedList[3].number}} messages
                    </div>
                  </div>
                  <div class = 'typeSection_list_component_blank'></div>
                  <div class = 'typeSection_list_component_right'>35일 전</div>
                </div>

                <div class = 'typeSection_list_component2'>
                  <div class = 'typeSection_list_component_left'>
                    <div :class="{
                      'typeNumberCSS_URL': sortedArrayRankedList[4].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[4].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[4].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[4].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[4].title == '[국외발신]' ,
                    }"> 
                      5. <b>{{this.sortedArrayRankedList[4].title}}</b>
                    </div>
                    <div :class="{
                      'typeNumberCSS_URL':sortedArrayRankedList[4].title == 'URL포함 문자' ,
                      'typeNumberCSS_WEB':sortedArrayRankedList[4].title == '[Web발신] 문자' ,
                      'typeNumberCSS_ZERO':sortedArrayRankedList[4].title == '070문자' ,
                      'typeNumberCSS_AD':sortedArrayRankedList[4].title == '[광고] 문자' ,
                      'typeNumberCSS_OVERSEA':sortedArrayRankedList[4].title == '[국외발신]' ,
                    }"> 
                      {{this.sortedArrayRankedList[4].number}} messages
                    </div>
                  </div>
                  <div class = 'typeSection_list_component_blank'></div>
                  <div class = 'typeSection_list_component_right'>2일 전</div>
                </div>
              </div>
            

            </v-sheet>
          </v-bottom-sheet>

        <!--##########################시간!!!!!######################--->
          <v-bottom-sheet
            class="overflow-y-auto"
            v-model="GahyeonTime"
            fullscreen
            overlay-color="white"
            max-height="auto"
            scrollable
          >
            <v-sheet class="overflow-y-auto" style="text-align: center">
              
              <div class="analysis_top">
                <b-icon-x id="x_button" @click="GahyeonTime = !GahyeonTime" />
                <div id="analysis_title">
                  <img src="assets/we_ka_t.png" id="anlysis_title_img" />
                </div>
                <div id="empty_box_2"></div>
              </div>

              <div id="analysis_type_long">
                기간, 시간별로 수신한 피싱의심문자의 빈도수를 확인할 수
                있습니다.
              </div>

              <br/>
              
              <v-tabs
                v-model="timeTabsCurrentItem"
                background-color="light-grey"
                color="blue"
                grow
              >
                <v-tab><b>1개월</b></v-tab>
                <v-tab><b>3개월</b></v-tab>
                <v-tab><b>전체</b></v-tab>
              </v-tabs>

              <br/>

              <v-tabs-items v-model="timeTabsCurrentItem">
                <v-tab-item>
                  <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%; font-family: 'Gowun Dodum', sans-serif"
                >
                  <swiper-slide>2021-11-01 ~ 2021-11-08</swiper-slide>
                  <swiper-slide>2021-11-09 ~ 2021-11-17</swiper-slide>
                  <swiper-slide>2021-11-18 ~ 2021-11-25</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>


              
              <bar-chart
                :chart-data="bar_datacollection"
                :options="bar_option"
                style="margin: 10% 5% 10% 5%"
              >
              </bar-chart>
              <div
                style="
                  width: 100%;
                  margin: auto;
                  height: 6px;
                  background: #e6e6e6;
                  border: #e6e6e6 1px solid;
                "
              />

              <div id="analysis_safety_score">타임 리포트</div>
              <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%"
                >
                  <swiper-slide>2021-11-01</swiper-slide>
                  <swiper-slide>2021-11-02</swiper-slide>
                  <swiper-slide>2021-11-03</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>
              <line-chart
                :chart-data="line_datacollection"
                :options="line_option"
                style="margin: 10% 5% 10% 5%"
              ></line-chart>
                </v-tab-item>

                <v-tab-item>
                 <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%; font-family: 'Gowun Dodum', sans-serif"
                >
                  <swiper-slide>2021-11-01 ~ 2021-11-08</swiper-slide>
                  <swiper-slide>2021-11-09 ~ 2021-11-17</swiper-slide>
                  <swiper-slide>2021-11-18 ~ 2021-11-25</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>


              
              <bar-chart
                :chart-data="bar_datacollection"
                :options="bar_option"
                style="margin: 10% 5% 10% 5%"
              ></bar-chart>
              <div
                style="
                  width: 100%;
                  margin: auto;
                  height: 6px;
                  background: #e6e6e6;
                  border: #e6e6e6 1px solid;
                "
              />

              <div id="analysis_safety_score">타임 리포트</div>
              <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%"
                >
                  <swiper-slide>2021-11-01</swiper-slide>
                  <swiper-slide>2021-11-02</swiper-slide>
                  <swiper-slide>2021-11-03</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>
              <line-chart
                :chart-data="line_datacollection"
                :options="line_option"
                style="margin: 10% 5% 10% 5%"
              ></line-chart>
                </v-tab-item>

                <v-tab-item>
                  <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%; font-family: 'Gowun Dodum', sans-serif"
                >
                  <swiper-slide>2021-11-01 ~ 2021-11-08</swiper-slide>
                  <swiper-slide>2021-11-09 ~ 2021-11-17</swiper-slide>
                  <swiper-slide>2021-11-18 ~ 2021-11-25</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>


              
              <bar-chart
                :chart-data="bar_datacollection"
                :options="bar_option"
                style="margin: 10% 5% 10% 5%"
              ></bar-chart>
              
              <div
                style="
                  width: 100%;
                  margin: auto;
                  height: 6px;
                  background: #e6e6e6;
                  border: #e6e6e6 1px solid;
                "
              />

              <div id="analysis_safety_score">타임 리포트</div>
              <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%"
                >
                  <swiper-slide>2021-11-01</swiper-slide>
                  <swiper-slide>2021-11-02</swiper-slide>
                  <swiper-slide>2021-11-03</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>
              
              <line-chart
                :chart-data="line_datacollection"
                :options="line_option"
                style="margin: 10% 5% 10% 5%"
              >
              </line-chart>
                </v-tab-item>
              </v-tabs-items>


              
            </v-sheet>
          </v-bottom-sheet>

         <!--#####################스미싱관련문자####################--->
          <v-bottom-sheet
            class="overflow-y-auto"
            v-model="GahyeonSms"
            fullscreen
            overlay-color="white"
            max-height="auto"
            scrollable
          >
            <v-sheet class="overflow-y-auto" style="text-align: center">
              <div class="analysis_top">
                <b-icon-x id="x_button" @click="GahyeonSms = !GahyeonSms" />
                <div id="analysis_title">
                  <img src="assets/we_ka_t.png" id="anlysis_title_img" />
                </div>
                <div id="empty_box_2"></div>
              </div>
              <div id="analysis_type">문자</div>
              <br />
              <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%"
                >
                  <swiper-slide>2021-11-01 ~ 2021-11-08</swiper-slide>
                  <swiper-slide>2021-11-09 ~ 2021-11-17</swiper-slide>
                  <swiper-slide>2021-11-18 ~ 2021-11-25</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>
              <div id="analysis_safety_score">스미싱 의심 관련 문자</div>
              
              <div id="analysis_smishing">
                <img src="assets/receive_sms1.svg" style="width: 20%" />
                <div style="width: 60%; font-size: 15px; font-family: 'Gowun Dodum', sans-serif;">
                  등록된 번호
                </div>
                <div
                  style="
                    width: 20%;
                    color: green;
                    font-family: 'Gowun Dodum', sans-serif;">
                  {{inSMS}}건
                </div>
              </div>
              
              <div class="space_line"></div>

              <div id="analysis_smishing">  
                <img src="assets/unkown_sms.svg" style="width: 20%" />
                <div style="
                    width: 60%;
                    font-size: 15px;
                    font-family: 'Gowun Dodum', sans-serif;">
                  등록되지 않은 번호
                </div>
                <div
                  style="
                    width: 20%;
                    color: green;
                    font-family: 'Gowun Dodum', sans-serif;
                  "
                >
                  {{notInSMS}}건
                </div>
              </div>

              <div class="space_line"></div>


              <div id="analysis_smishing_last">
                <img src="assets/phishing_doubt_sms.svg" style="width: 20%" />
                <div style="width: 20%"></div>
                <div style=" font-size: 10px; width: 60%; font-family: 'Gowun Dodum', sans-serif;">
                  * 정식 스토어에 등록하지 않고 설치된 앱은 피싱 범죄에 악용될
                  수 있습니다.
                </div>

                <div style="width: 20%; color: green; font-family: 'Gowun Dodum', sans-serif;">
                  {{adSMS}}건
                </div>
              </div>
              <div id="install_sms_name">
                <div id="install_sms">설치 의심 관련 문자</div>
                <div>
                  <span id="sms_name">홍길동</span>
                  <span id="sms_time">&nbsp;2021년 9월 17일 20시 16분</span>
                </div>
                <div id="sms_content">
                  추석 맞이 대박 세일! 해당 앱을 설치하세요!
                </div>
              </div>
            </v-sheet>
          </v-bottom-sheet>

        <!--#########################단어!!!!###############################--->
          <v-bottom-sheet
            class="overflow-y-auto"
            v-model="GahyeonWord"
            fullscreen
            overlay-color="white"
            max-height="auto"
            scrollable
          >
            <v-sheet class="overflow-y-auto" style="text-align: center">
              <div class="analysis_top">
                <b-icon-x id="x_button" @click="GahyeonWord = !GahyeonWord" />
                <div id="analysis_title" style="color: #0b80f5">단어</div>
                <div id="empty_box_2"></div>
              </div>
              <div id="grey_box" />
              <template>
                <swiper
                  class="swiper"
                  :options="swiperOption"
                  style="width: 80%"
                >
                  <swiper-slide>2021-11-01 ~ 2021-11-08</swiper-slide>
                  <swiper-slide>2021-11-09 ~ 2021-11-17</swiper-slide>
                  <swiper-slide>2021-11-18 ~ 2021-11-25</swiper-slide>
                  <div class="swiper-button-prev" slot="button-prev"></div>
                  <div class="swiper-button-next" slot="button-next"></div>
                </swiper>
              </template>
              <br />
              <v-text-field
                label="검색어"
                placeholder=""
                outlined
                dense
                single-line
                color="blue"
                class="shrink mx-4"
                style="
                  border-radius: 5px;
                  font-family: 'Gowun Dodum', sans-serif;
                  height: 6%;
                "
              >
              </v-text-field>
              <div id="search">
                <div style="color: #0b80f5; font-size: 15px">
                  검색 결과 {{ result }}건
                </div>
                <div style="color: grey; font-size: 15px">(최대 10000건)</div>
              </div>
              <div
                id="search_result"
                v-for="(item, i) in search_name"
                :key="item"
              >
                <div style="font-size: 15px">
                  {{ i + 1 }}. {{ search_name[i] }}
                </div>
                <div style="font-size: 12px; color: blue">
                  {{ search_events[i] }}건
                </div>
              </div>
            </v-sheet>
          </v-bottom-sheet>

	</div>

  <div id='bottom_sheet'>
      <div class="text-center">
            <v-bottom-sheet v-model="sheet" inset>
                <template v-slot:activator="{ on, attrs }">
                  <div id="bottom_sheet_div" >
                    <div id="bottom_sheet_button_column" v-bind="attrs" v-on="on">
                      
                      <div id="bottom_sheet_button_row">
                        <div class="bottom_sheet_sized_box"></div>
                        <div id="bottom_sheet_menu_icon"><v-icon color="white">menu</v-icon></div>
                        <div class="bottom_sheet_sized_box"></div>
                      </div>
                      
                      <div id="bottom_sheet_button_title">관련 컨텐츠</div>
                    </div>
                  </div>
                    <!-- <v-btn color="grey" dark v-bind="attrs" v-on="on" style="width:50%">
                        <v-icon>menu</v-icon>
                    </v-btn> -->
                </template>
                <v-sheet class="text-center" height="200px">
                    <v-btn class="mt-6" text color="error" v-on:click="sheet = !sheet">
                        close
                    </v-btn>
                    <div class="my-3">
                        This is a bottom sheet using the inset prop
                    </div>
                </v-sheet>
            </v-bottom-sheet>
      </div>
	</div>

    <div v-if='smsDetailPageToggle' v-bind:style="bottomSheet_style1">

        <div v-bind:style="smsDetailPageRow"> 
            <div v-bind:style="backButtonStyle" v-on:click="smsDetailPageToggle=!smsDetailPageToggle">back</div>

            <div v-bind:style="smsDetailPageColumn"> 
                <div v-bind:style="detailTopMenuStyle"> 
                    <div v-bind:style='detailTopMenuStyle_component1' v-on:click='getFullSMS'>전체 문자</div>
                    <div v-bind:style='detailTopMenuStyle_component2' v-on:click='getPhoneBook'>연락처 정보</div>
                    <div v-bind:style='detailTopMenuStyle_component3' v-on:click='getNotBookedSMS'>연락X 문자</div>
                    <div v-bind:style='detailTopMenuStyle_component4' v-on:click='getBookedSMS'>연락O 문자</div>
                </div>
                <div v-bind:style="detailTopMenuStyle"> 
                    <div v-bind:style='detailTopMenuStyle_component2' v-on:click='getURLSMS'>URL</div>
                    <div v-bind:style='detailTopMenuStyle_component3' v-on:click='getWebSMS'>[Web]</div>
                    <div v-bind:style='detailTopMenuStyle_component4' v-on:click='getZeroSevenSMS'>070</div>
                    <div v-bind:style='detailTopMenuStyle_component5' v-on:click='getAdSMS'>(광고)</div>
                    <div v-bind:style='detailTopMenuStyle_component1' v-on:click='getOverseaSMS'>(국외)</div>
                </div>
                <div v-bind:style="detailTopMenuStyle"> 
                    <div v-bind:style='detailTopMenuStyle_component2' v-on:click='getBadSMS'>피싱문자</div>
                </div>
            </div>

            <div v-bind:style='statisticsStyle' v-on:click='statistics'>통계</div>    
        </div>
        
        <div v-for="smsComponent of vforList" v-bind:key="smsComponent" v-bind:style="smsComponentStyle">
            {{smsComponent.number}}
            <br>
            <br>
        </div>

    </div>

  </div>
   </v-app>
</template>

<script>
import { Swiper, SwiperSlide } from "vue-awesome-swiper"
import "swiper/css/swiper.css"

// import VueBarGraph from 'vue-bar-graph';
import LineChart from "../components/LineChart.js";
import BarChart from "../components/BarChart.js";

import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getFirestore, collection, getDocs, doc, setDoc } from 'firebase/firestore/lite';

var numberList = [];
var fullSmsList = [];
var phoneBookList = [];
var notInPhoneBookList = [];
var inPhoneBookList = [];

var urlList = [];
var webList = [];
var zeroSevenList = [];
var adList = [];
var overseaList = [];
var badList = [];

var totalScore = 0

//각 유형별 문자 갯수들
var urlSMS = 0;
var webSMS = 0;
var zeroSevenSMS = 0;
var adSMS = 0;
var overseaSMS = 0;
var badSMS = 0;


var i = 0;
export default {
    components:{
        Swiper, 
        SwiperSlide,
        LineChart,
        BarChart
    },
    data: function(){
        return{
          score_number: totalScore,
          level:0,
          isLoading: false,
        
            timeTabsCurrentItem:0,
            smsDetailPageToggle:false,
            
            toggle: false,
            tempSMSNumber: 0,
            listSize: numberList.length,


            //갯수들
            fullSMS: 0,
            notInSMS: 0,    
            inSMS:0,
            urlSMS:0,
            webSMS:0,
            zeroSevenSMS:0,
            adSMS:0,
            overseaSMS:0,
            badSMS: 0,
            uisimSMS: 0,

            drawer: false,
            sheet: false,
            vforList: [],

           
            
            rankList:[
              {title:'URL포함 문자', number: 0, color:'#7bcabf'},
              {title:'[Web발신] 문자', number: 0, color:'#b5c7ed'},
              {title:'070문자', number: 0, color:'#7fc638'},
              {title:'[광고] 문자', number: 0, color:'#f55354'},
              {title:'[국외발신]',  number: 0, color:'#f59b25'}, 
            ],


            ////////////////////////가현/////////////////////////////////////
            GahyeonSmsDetailPageToggle: false,
            GahyeonType: false,
            GahyeonTime: false,
            GahyeonSms: false,
            GahyeonWord: false,
            swiperOption: {
                slidesPerView: 1,
                spaceBetween: 30,
                navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev'
                }
            },
            
            bar_datacollection: {
                labels: ["일", "월", "화", "수", "목", "금", "토"],
                datasets: [{
                  barPercentage: 0.5,
                  barThickness: 8,
                  minBarLength: 2,
                  data: [0, 3, 8, 6, 2, 5, 15],
                  backgroundColor: "#0473e1",
                }],
            },
            bar_option: {
                legend: {
                  display: false
                },
                scales: {
                xAxes: [{
                    gridLines: {
                    display: false,
                    }
                }],
                yAxes: [{
                    gridLines: {
                    display: false,
                    }
                }]
                }
            },
            typeSections: [
                { value: urlSMS, label: 'URL포함 문자', color: '#7bcabf' },
                { value: webSMS, label: '[Web발신] 문자', color: '#b5c7ed' },
                { value: zeroSevenSMS, label: '070문자', color: '#7fc638' },
                { value: adSMS, label: '[광고] 문자', color: '#f55354' },
                { value: overseaSMS, label: '[국외발신]', color: '#f59b25' },
            ],

            line_datacollection: {
                labels: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24'],
                datasets: [
                {
                    data: [5, 1, 2, 1, 8, 5, 6, 7, 8, 9, 0, 1, 2, 1, 4, 5, 6, 4, 8, 9, 0, 1, 2, 12, 5],
                    backgroundColor: "#0473e1",
                    borderColor: "#0473e1",
                    fill: false
                }]
            },
            line_option: {
                title: {
                text: '시간',
                align: 'center'
                },
                legend: {
                display: false
                },
                scales: {
                xAxes: [{
                    gridLines: {
                    display: false
                    }
                }],
                yAxes: [{
                    gridLines: {
                    display: false
                    }
                }]
                }
            },
            search_name: [
                '홍길동', '류성룡', '이순신'
            ],
            search_events: [150, 200, 100],
            ////////////////////////가현/////////////////////////////////////
            smsDetailPageRow: {
                display: 'flex',
                flexDirection: 'row', 
                alignItems: 'center',
                justifyContent: 'center', 
                
                width:'100%', 
                height:'20%',
                backgroundColor: 'chartreuse', 
            },
            backButtonStyle:{
                flex: '1 1 0',
                backgroundColor:'red',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
            smsDetailPageColumn:{
                flex: '5 1 0',
                display: 'flex',
                flexDirection: 'column', 
                alignItems: 'center',
                justifyContent: 'center',  
                height:'100%',
                width:'100%',  
                backgroundColor:'white',   
            },
            detailTopMenuStyle:{
                flex: '1 1 0',
                display: 'flex',
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
                
                backgroundColor:'blue',
                height:'100%',
                width:'100%', 
            },
             detailTopMenuStyle_component1: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'yellow',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
             detailTopMenuStyle_component2: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'tomato',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
             detailTopMenuStyle_component3: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'darksalmon',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
             detailTopMenuStyle_component4: {
                flex: '1 1 0',
                
                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
                
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor:'khaki',
                height:'100%',
            },
            detailTopMenuStyle_component5: {
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'hotpink',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
            statisticsStyle:{
                flex: '1 1 0',
                borderWidth: '2px',
                fontSize: '10px',
                backgroundColor: 'yellow',
                height:'100%',

                display:'flex', 
                flexDirection: 'row',       
                alignItems: 'center',
                justifyContent: 'center', 
            },
            smsComponentStyle:{
                backgroundColor: 'chartreuse',
                fontSize: '10px',
                margin: '10px',
            },
            bottomSheet_style:{
                backgroundColor: 'aquamarine',
                position: 'absolute',
                top:'90%',
                width: '80%',
                height: '50%',
                fontSize: '20px',
            },
            bottomSheet_style1:{
                backgroundColor: 'deepskyblue',
                position: 'absolute',
                width: '80%',
                height: '50%',
                fontSize: '10px',
                overflow: 'scroll',
            },
           
        }
    },
    computed:{
      sortedArrayRankedList: function() {
          function compare(a, b) {
            if (b.number < a.number)
              return -1;
            if (b.number > a.number)
              return 1;
            return 0;
          }

          return this.rankList.sort(compare);
      },

      sortedArrayTypeSection: function() {
          function compareSection(a, b) {
            if (b.value < a.value)
              return -1;
            if (b.value > a.value)
              return 1;
            return 0;
          }

          return this.typeSections.sort(compareSection);
      }
    },
   
    methods:{
        theFormat(number) {
            return number.toFixed(2);
        },
        completed() {
            console.log('Animation ends!');
        },
        playAnimation() {
            this.$refs.number1.restart();
        },
        async isLoadingToggle(){
           this.isLoading = !this.isLoading;
        },

        async phishingAnalysis(){
          // this.isLoading = true;

          await this.developerOptionToggle();

          // this.isLoading = false;


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

          setDoc(doc(db, "01051158604", '01051158604'), {
                  totalScore: totalScore,
                  state: "CA",
                  country: "USA"
          });

          this.GahyeonSmsDetailPageToggle = !this.GahyeonSmsDetailPageToggle;
          this.playAnimation();
        },
        setTypePage(){
          this.typeSections =[];
          urlSMS = this.urlSMS/this.notInSMS * 100;
          webSMS = this.webSMS/this.notInSMS * 100;
          zeroSevenSMS = this.zeroSevenSMS/this.notInSMS * 100;
          adSMS = this.adSMS/this.notInSMS * 100;
          overseaSMS = this.overseaSMS/this.notInSMS * 100;
          
          this.rankList = [];
          //바깥 쪽의 var을 쓴 이유는 그것이 
          this.typeSections.push({value: urlSMS, label: 'URL포함 문자', color: '#7bcabf'});
          this.typeSections.push({value: webSMS, label: '[Web발신] 문자', color: '#763931' });
          this.typeSections.push({value: zeroSevenSMS, label: '070문자', color: '#7fc638' });
          this.typeSections.push({value: adSMS, label: '[광고] 문자', color: '#f55354' });
          this.typeSections.push({value: overseaSMS, label: '[국외발신]', color: '#f59b25' });  

          this.rankList.push({title:'URL포함 문자', number: this.urlSMS, color:'#f59b25'});
          this.rankList.push({title:'[Web발신] 문자', number: this.webSMS, color:'#0033ff' });
          this.rankList.push({title:'070문자', number: this.zeroSevenSMS, color:'#7fc638'});
          this.rankList.push({title:'[광고] 문자', number: this.adSMS, color:'#f55354'});
          this.rankList.push({title:'[국외발신]', number: this.overseaSMS, color:'#f59b25'}); 
        },
        async developerOptionToggle()  {
            // this.drawer = !this.drawer;
            fullSmsList = [];
            phoneBookList = [];
            notInPhoneBookList = [];
            inPhoneBookList = [];
            
            urlList = [];
            webList = [];
            zeroSevenList = [];
            adList = [];
            overseaList = [];

            await this.cordovaGetPhoneBook();
            await this.cordovaGetFullSMS();
            await this.cordovaGetNotBookedSMS();
            await this.cordovaGetBookedSMS();
            await this.cordovaGetURLSmsHashMap();
            await this.cordovaGetWebSmsHashMap();
            await this.cordovaGetZeroSevenSmsHashMap();
            await this.cordovaGetAdSmsHashMap();
            await this.cordovaGetOverseaSmsHashMap();

            this.fullSMS = fullSmsList.length-1;
            this.inSMS = inPhoneBookList.length-1;            
            this.notInSMS = notInPhoneBookList.length-1;

            this.urlSMS = urlList.length-1;
            this.webSMS = webList.length-1;
            this.zeroSevenSMS = zeroSevenList.length-1;
            this.adSMS = adList.length-1;
            this.overseaSMS = overseaList.length-1;
            this.badSMS = badList.length - 1;
            
            
            this.setTypePage();
            await this.cordovaGetTotalScore();
            
            this.score_number = parseInt(totalScore);

            if(totalScore>=80){
              this.level = 4;
            }
            else if(totalScore>=70 && totalScore<80){
              this.level = 3;
            }
            else if(totalScore>=60 && totalScore<70){
              this.level = 2;
            }
            else if(totalScore<50){
              this.level = 1;
            }
        },

        async secretDeveloperMenu()  {
            this.drawer = !this.drawer;

            //리스트들 싹 비워주고요
            fullSmsList = [];
            phoneBookList = [];
            notInPhoneBookList = [];
            inPhoneBookList = [];
            urlList = [];
            webList = [];
            zeroSevenList = [];
            adList = [];
            overseaList = [];
            badList = [];

            //코르도바 플러그인을 돌려주는데 데이터를 가져오면서 리스트에 동시에 넣어줍니다.
            await this.cordovaGetPhoneBook();
            await this.cordovaGetFullSMS();
            await this.cordovaGetNotBookedSMS();
            await this.cordovaGetBookedSMS();
            await this.cordovaGetURLSmsHashMap();
            await this.cordovaGetWebSmsHashMap();
            await this.cordovaGetZeroSevenSmsHashMap();
            await this.cordovaGetAdSmsHashMap();
            await this.cordovaGetOverseaSmsHashMap();
            await this.cordovaGetBadSmsHashMap();
          

            //각각 문자 종류 별 갯수도 지정해주고.
            this.fullSMS = fullSmsList.length-1;
            this.inSMS = inPhoneBookList.length-1;            
            this.notInSMS = notInPhoneBookList.length-1;

            this.urlSMS = urlList.length-1;
            this.webSMS = webList.length-1;
            this.zeroSevenSMS = zeroSevenList.length-1;
            this.adSMS = adList.length-1;
            this.overseaSMS = overseaList.length-1;
            this.badSMS = badList.length-1;
            
            //유형페이지도 최신화 해줍니다.
            this.setTypePage();

            //안드로이드에서 가져오면서 측정한 점수도 가져옵니다.
            await this.cordovaGetTotalScore();
            this.score_number = parseInt(totalScore);

            //점수를 기반으로 레벨을 정해줍니다.
            if(totalScore>=80){
              this.level = 4;
            }
            else if(totalScore>=70 && totalScore<80){
              this.level = 3;
            }
            else if(totalScore>=60 && totalScore<70){
              this.level = 2;
            }
            else if(totalScore<50){
              this.level = 1;
            }
            this.smsDetailPageToggle = !this.smsDetailPageToggle;
        },
  
        ////////   개발자 문자 분석용 메소드들 
        statistics(){
            this.vforList.splice(0);
            this.vforList.push({number:'전체 문자 : ' + this.fullSMS});
            this.vforList.push({number:'연락처에 있는 문자 : ' + this.inSMS});
            this.vforList.push({number:'연락처에 없는 문자 : ' + this.notInSMS});
            this.vforList.push({number:'(연락처에 없는 문자 중)URL포함 문자 : ' + this.urlSMS});
            this.vforList.push({number:'(연락처에 없는 문자 중)[Web발신] 문자 : ' + this.webSMS});
            this.vforList.push({number:'(연락처에 없는 문자 중)070문자 : ' + this.zeroSevenSMS});
            this.vforList.push({number:'(연락처에 없는 문자 중)[광고] 문자 : ' + this.adSMS});
            this.vforList.push({number:'(연락처에 없는 문자 중)[국외발신] 문자 : ' + this.overseaSMS});
        },
        getFullSMS(){
            this.vforList.splice(0);
            i = 0;
            
            while(i<this.fullSMS){
                this.vforList.push({number:fullSmsList[i]});
                i++;
            }
        },
        getPhoneBook(){
            this.vforList.splice(0);
            i = 0;
            while(i < phoneBookList.length-1){
                this.vforList.push({number: phoneBookList[i]});
                i++;
            }
        },
        getNotBookedSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.notInSMS){
                this.vforList.push({number: notInPhoneBookList[i]});
                i++;
            }
        },
        getBookedSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.inSMS){
                this.vforList.push({number: inPhoneBookList[i]});
                i++;
            }
        },
        getURLSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.urlSMS){
                this.vforList.push({number: urlList[i]});
                i++;
            }
        },
        getWebSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.webSMS){
                this.vforList.push({number: webList[i]});
                i++;
            }
        },
        getZeroSevenSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.zeroSevenSMS){
                this.vforList.push({number: zeroSevenList[i]});
                i++;
            }
        },
        getAdSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.adSMS){
                this.vforList.push({number: adList[i]});
                i++;
            }
        },
        getOverseaSMS(){
            this.vforList.splice(0);
            i=0;
            while(i < this.overseaSMS){
                this.vforList.push({number: overseaList[i]});
                i++;
            }
        },

        getBadSMS(){
          this.vforList.splice(0);
          i=0;
          while(i < this.badSMS){
              this.vforList.push({number: badList[i]});
              i++;
          }
        },

        //////////// 코르도바 플러그인 메소드들 
        async cordovaGetTotalScore(){
            return new Promise(function(resolve, reject){
                cordova.exec(getTotalScore, null,"CordovaCustomPlugin", "getTotalScore", []);
                resolve();
            });
        },
        async cordovaGetFullSMS(){
            return new Promise(function(resolve, reject){
                cordova.exec(refreshSMSDataBase, null,"CordovaCustomPlugin", "refreshSMSDataBase", []);
                resolve();
            });
        },
        async cordovaGetPhoneBook(){
            return new Promise(function (resolve, reject){
                cordova.exec(refreshPhoneBookDataBase, null,"CordovaCustomPlugin", "refreshPhoneBookDataBase", []);
                resolve();
            });
        },
        async cordovaGetNotBookedSMS(){
            return new Promise(function (resolve, reject){
                cordova.exec(function_getNotBookedSMS, null,"CordovaCustomPlugin", "getNotBookedSMS", []);
                resolve();
            });
        },
        async cordovaGetBookedSMS(){
            return new Promise(function (resolve, reject){
                cordova.exec(function_getBookedSMS, null,"CordovaCustomPlugin", "getBookedSMS", []);
                resolve();
            });
        },
        //특수한 문자들 
        async cordovaGetURLSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getURLSmsHashMap, null,"CordovaCustomPlugin", "getURLSmsHashMap", []);
                resolve();
            });
        },
        async cordovaGetWebSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getWebSmsHashMap, null,"CordovaCustomPlugin", "getWebSmsHashMap", []);
                resolve();
            });
        },
        async cordovaGetZeroSevenSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getZeroSevenSmsHashMap, null,"CordovaCustomPlugin", "getZeroSevenSmsHashMap", []);
                resolve();
            });
        },
        async cordovaGetAdSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getAdSmsHashMap, null,"CordovaCustomPlugin", "getAdSmsHashMap", []);
                resolve();
            });
        },
        async cordovaGetOverseaSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getOverseaSmsHashMap, null,"CordovaCustomPlugin", "getOverseaSmsHashMap", []);
                resolve();
            });
        },
        async cordovaGetBadSmsHashMap(){
             return new Promise(function (resolve, reject){
                cordova.exec(function_getBadSmsHashMap, null,"CordovaCustomPlugin", "getBadSmsHashMap", []);
                resolve();
            });
        }
    }
}

function function_getBadSmsHashMap(result){
  badList = result.split('^&');
}

function getTotalScore(result){
  // this.level = 5;
    totalScore = result;
}
function function_getURLSmsHashMap(result){
    urlList = result.split('^&');
    this.rankList.push({title:'d', number: '6', color:'#f59b25'});
}

function function_getWebSmsHashMap(result){
    webList= result.split('^&');
}

function function_getZeroSevenSmsHashMap(result){
    zeroSevenList= result.split('^&');
}

function function_getAdSmsHashMap(result){
    adList= result.split('^&');
}

function function_getOverseaSmsHashMap(result){
    overseaList= result.split('^&');
}



function refreshSMSDataBase(result){
    fullSmsList = result.split('^&');
}
function refreshPhoneBookDataBase(result){
    phoneBookList = result.split('@');
}
function function_getNotBookedSMS(result){
    notInPhoneBookList = result.split('^&');
}
function function_getBookedSMS(result){
    inPhoneBookList = result.split('^&')
}
</script>

<style scoped>
#catFace_img{
  height: 90%;
}

.bottom_sheet_sized_box{
  flex: 1 1 0;
}
#bottom_sheet_menu_icon{
  flex: 1 1 0;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;

  background-color: grey;
}

#bottom_sheet_button_row{
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: white;
}

#bottom_sheet_button_column{
  /* background-color: grey; */
  width: 70%;
}
#bottom_sheet_button_title{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 50px;
  width: 100%;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;

  background-color: grey;
  color:white;
}

#bottom_sheet_div{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
#bottom_sheet_button{
  background-color:grey;
  width: 50%;
}

.typeNumberCSS_URL{
  color: #7bcabf;
}
.typeNumberCSS_WEB{
  color: #763931;
}
.typeNumberCSS_ZERO{
  color: #7fc638 ;
}
.typeNumberCSS_AD{
  color: #f55354;
}
.typeNumberCSS_OVERSEA{
  color: #f59b25;
}

.space_line{
  background-color:#edf0f0;
  height: 2px;
  width: 100%;
}
/*유형 페이지*/
#typeSection_list{
  display: flex;
  flex-direction: column;
  width: 100vw;
}
.typeSection_list_component{
  display: flex;
  flex-direction: row;
  width: 100%;
  border-top: solid;
  border-color: #9fa0a0;
}
.typeSection_list_component2{
  display: flex;
  flex-direction: row;
  width: 100%;
  border-top: solid;
  border-bottom: solid;
  border-color: #9fa0a0;
}
.typeSection_list_component_left{
  flex: 2 1 0;
  text-align: start;
  margin-left: 5%;
  
}
.typeSection_list_component_blank{
  flex: 2.5 1 0;
}
.typeSection_list_component_right{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex: 1 1 0;
}
.typeSection_list_component_left_number{
  color:sorted;
}
/*유형 페이지*/
.score_current_green{
  
	display: flex;
  flex-direction: row;
  justify-content: flex-end;
	align-items:flex-end;
	
	height: 100%;
	width:50%;
	line-height:90%;
	font-size: 70px;
	color:#009944;
	/* background-color: aquamarine; */
}
.score_current_yellow{
	display: flex;
  flex-direction: row;
  justify-content: flex-end;
	align-items:flex-end;
	
	height: 100%;
	width:50%;
	line-height:90%;
	font-size: 70px;
	color:#ffcc00;
	/* background-color: aquamarine; */
}
.score_current_orange{
	display: flex;
  flex-direction: row;
  justify-content: flex-end;
	align-items:flex-end;
	
	height: 100%;
	width:50%;
	line-height:90%;
	font-size: 70px;
	color:#ff9e01;
	/* background-color: aquamarine; */
}
.score_current_red{
	display: flex;
  flex-direction: row;
  justify-content: flex-end;
	align-items:flex-end;
	
	height: 100%;
	width:50%;
	line-height:90%;
	font-size: 70px;
	color:#fd0000;
	/* background-color: aquamarine; */
}
.score_current_blue{
	display: flex;
  flex-direction: row;
  justify-content: flex-end;
	align-items:flex-end;
	
	height: 100%;
	width:50%;
	line-height:90%;
	font-size: 70px;
	color:#0473e1;
	/* background-color: aquamarine; */
}


#score_total{
	display: flex;
	height: 100%;
	width:50%;
	align-items: flex-end;
	font-size: 40px;
    
    line-height: 100%;
	color:#9fa0a0;
	/* background-color: burlywood; */
}
#detail_score_text{
    color:#9fa0a0;
}
#logo_img{
    width: 80%;
}
.home_container {
  display: flex;
  flex-direction: column;

  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100vw;
  padding: 0px;
  margin: 0px;

  position: sticky;
  overflow: hidden;
}
.detail_score_section{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
	flex: 1 1 0;
}
#sms_number{
    color:#009944;
}
#question_number{
    color:#ffcc00;
}


#home_appBar {
  max-height: 100px;
  display: flex;
  flex-direction: row;
  flex: 1.5 1 0;
  width: 100%;
  /* background-color: burlywood; */
}

#home_cat {
  flex: 4.3 1 0;
  width: 90%;
  /* background-color: #009944; */
}

#home_score_column{
  width: 100%;
  flex: 4 1 0;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  
}

#home_score_row{
  width: 100%;
  flex: 1.5 1 0;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  /* background-color:blanchedalmond; */
}


#home_score_ment{
	width: 100%;
	flex: 1 1 0;

	display: flex;
	flex-direction: column;
  	align-items: center;
  	justify-content: center;
	font-size:120%;
	color:#009944;
}
#home_score_ment1{
	width: 100%;
	flex: 1 1 0;

	display: flex;
	flex-direction: column;
  	align-items: center;
  	justify-content: center;
	font-size:120%;
	color:#fae100;
}
#home_score_ment2{
	width: 100%;
	flex: 1 1 0;

	display: flex;
	flex-direction: column;
  	align-items: center;
  	justify-content: center;
	font-size:120%;
	color:#ff9e01;
;
}
#home_score_ment3{
	width: 100%;
	flex: 1 1 0;

	display: flex;
	flex-direction: column;
  	align-items: center;
  	justify-content: center;
	font-size:120%;
	color:#e60012;
}

#detail_button{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    /* background-color: #e60012; */
    width: 100%;
	flex: 2.2 1 0;
	/* background-color: cyan; */
}

#detail_text{	
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

  
	width: 100%;
	flex: 3 1 0;
	/* background-color:gold; */
}

#detail_text_text{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
    color:#9fa0a0;
}

.level_green{
    font-weight: bold;
    color:#009944;
}
.level_yellow{
    font-weight: bold;
    color:#fae100;
}
.level_orange{
  
    font-weight: bold;
    color:#ff9e01;
}
.level_red{
    font-weight: bold;
    color:#e60012;
}

#detail_score{
	width: 100%;
	flex: 4 1 0;

	display: flex;
	flex-direction: row;
	/* background-color:mistyrose; */
}

#bottom_sheet{
	width: 100%;
	/* flex: 1.5 1 0; */
	/* background-color:#763931; */
  position: relative;
}
/***********************************************************/


#logo {
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 2 1 0;
  /* background-color: aqua; */
}
#empty_box {
  flex: 7 1 0;
  /* background-color: darkblue; */
}
#dots {
  display: flex;
  align-items: center;
  justify-content: center;
  flex: 1 1 0;
  /* background-color: darkkhaki; */
}

.background_question {
    display: flex;
    align-items: center;
    justify-content: center;

    height: 100%;
    background-image: url('assets/background_question.png');
    background-repeat: repeat;
	background-position-y: -10%;
}

.background_fish {
    display: flex;
    align-items: center;
    justify-content: center;

    height: 100%;
    background-image: url('assets/background_fish.svg');
    background-repeat: repeat;
	background-position-y: -10%;
}

.background_rain {
    display: flex;
    align-items: center;
    justify-content: center;

    height: 100%;
    background-image: url('assets/background_rain.svg');
    background-repeat: repeat;
	background-position-y: -10%;
}

.background_caution {
    display: flex;
    align-items: center;
    justify-content: center;

    height: 100%;
    background-image: url('assets/background_caution.svg');
    background-repeat: repeat;
	background-position-y: -10%;
}

.background_warning {
    display: flex;
    align-items: center;
    justify-content: center;

    height: 100%;
    background-image: url('assets/background_warning.svg');
    background-repeat: repeat;
	background-position-y: -10%;
}

#background_head{
	background-image: url('assets/catHead.svg');
    height: 123px;
    width: 184px;
    
	display: flex;
    align-items: center;
    justify-content: center;
    
	padding-top: 50px;
}


#detail_button_btn{
  display: flex;
  align-items: center;
  justify-content: center;

  height: 60%;
  width: 40%;
  border-radius: 25px;
  background-color: #0473e1;

  font-weight: bold;
  color:white;
}





/* !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!가현쓰!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
.analysis_top {
  margin: 3% 0 3% 0;
  display: flex;
  flex: 1 1 auto;
  height: 3%;
  align-items: center;
  justify-content: space-around;
}
#x_button {
  width: 10%;
  font-size: 40px;
}

#empty_box_2 {
  width: 10%;
}
#analysis_title {
  width: 50%;
  height: 20px;
  text-align: center;
  font-family: "Gowun Dodum", sans-serif;
}
#anlysis_title_img {
  width: 80px;
  height: 30px;
  object-fit: cover;
}
#analysis_safety_score {
  margin: 5% 0 5% 0;
  font-size: 25px;
  font-family: "Gowun Dodum", sans-serif;
  font-weight: bold;
}
#analysis_type {
  width: 100%;
  height: 50px;
  line-height: 50px;
  color: #0b80f5;
  background-color: #eaf5ff;
  text-align: center;
  font-family: "Gowun Dodum", sans-serif;
}
#analysis_type_long {
  width: 100%;
  height: 50px;
  color: #0b80f5;
  background-color: #eaf5ff;
  text-align: center;
  padding: 0 15% 0 15%;
  font-family: "Gowun Dodum", sans-serif;
}

#analysis_smishing {
  display: flex;
  flex: 5 1 0;
  width: 90%;
  margin: 5%;
  align-items: center;
  justify-content: space-around;
}
#analysis_smishing_last {
  display: flex;
  flex: 5 1 0;
  width: 90%;
  margin: 5%;
  align-items: center;
  justify-content: space-between;
}


#install_sms_name {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 2% 8% 2% 8%;
  text-align: left;
  font-family: "Gowun Dodum", sans-serif;
  background: #edf0f0;
}
#sms_name {
  float: left;
  font-weight: bold;
}
#sms_time {
  float: left;
  padding: 3% 0 0 0;
  font-size: 10px;
  color: grey;
}
#sms_content {
  font-size: 12px;
  color: grey;
}

#search {
  display: flex;
  justify-content: space-between;
  margin: 0 5% 0 5%;
  font-family: "Gowun Dodum", sans-serif;
}
#search_result {
  display: flex;
  width: 100%;
  height: 40px;
  margin: 1% 0 0 0;
  padding: 0 5% 0 5%;
  align-items: center;
  justify-content: space-between;
  background: rgb(148, 210, 230);
}
</style>
