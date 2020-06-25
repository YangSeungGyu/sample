@echo off & setlocal enabledelayedexpansion
:: ** 사용시 hosts 파일 수정 권한을 일반사용자에게 줘야 사용 가능함.
:: off 진행상황을 보여주지 않는다 . setlocal현재 파일에서면 유효하게 환경을 적용한다
:: enabledelayedexpansion !변수!의 값을 if나 for문이 끝나기 전에도 변수로 받아들여 적용한다(안그러면 해당문 내에선 초기값으로 유지됨)

::파일명위치 및 파일 
set hosts=./hosts

::dev host 셋팅
set dev=10.1111.222.333        api.test.co.kr
set dev1=10.1111.444.555        www.test.co.kr

::local host 셋팅
set local=127.0.0.1       api.test.co.kr
set local1=127.0.0.1        www.test.co.kr

::도메인 (해당 문자열 찾는 용도)
set domain=api.test.co.kr
set domain1=www.test.co.kr

echo.
:: findstr문자열을 찾는다./ i 대소문자 무관 />nul 진행 결과를 화면에 출력하지 않는다.
findstr /i %domain% %hosts%>nul
findstr /i %domain1% %hosts%>nul

::errorlevel 상단 명령의 결과 0은 성공 그외 에러발생
if %errorlevel% == 0 (
echo Host entry for denver exists.. Checking active status
)
:: show options------------------------------------------------------------------------------
:: echo. echo/는 공백띄우기
echo.
echo 1 : dev
echo 2 : local
::/ set /p = 사용자로부터 명령어를 입력 받음 / input 변수명이됨
set /p input= put the option's no :

:: selected dev------------------------------------------------------------------------------
::		/v일치하지 않는 줄만 표시한다 < 변경하지 않는내용 백업
::		>tmep 결과를 temp변수로  hostback.txt파일로 기록한다 - 근데 /가 여기선 어디고 파일은 어딨지??
::		백업한 내용으로 그대로 파일에 엎어씀.
if %input%==1 (

::1번url
findstr /i /v %domain% %hosts% >%temp%.\hostback.txt
type %temp%\hostback.txt >%hosts%
echo %dev% >>%hosts%

::2번url
findstr /i /v %domain1% %hosts% >%temp%.\hostback.txt
type %temp%\hostback.txt >%hosts%
echo %dev1% >>%hosts%

echo Host file is pointing to denvor now
)

:: selected local------------------------------------------------------------------------------
if %input%==2 (

::1번url
findstr /i /v %domain% %hosts% >%temp%.\hostback.txt
type %temp%\hostback.txt >%hosts%
echo %local% >>%hosts%

::2번url
findstr /i /v %domain1% %hosts% >%temp%.\hostback.txt
type %temp%\hostback.txt >%hosts%
echo %local1% >>%hosts%

:: end line------------------------------------------------------------------------------
echo done!!
Echo.. checking and confirming..
findstr /i %domain% %hosts%>nul
findstr /i %domain1% %hosts%>nul
echo.
if %errorlevel% == 0 echo Host file disabled successfully
)







