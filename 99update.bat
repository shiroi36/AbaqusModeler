
@ECHO O
rem githubのDrawingとつながっているフォルダです。
set path2=%userprofile%\Documents\git\Abaqusmodeler\
set path1=%cd%
rem path2に新たに作成するフォルダ名です。

@ECHO OFF
 
:INPUT_START
ECHO +-------------------------------------------------------+
ECHO  gitにアップロードするコメントを入力してください。
ECHO +-------------------------------------------------------+
SET INPUT_STR=
SET /P INPUT_STR=
 
IF "%INPUT_STR%"=="" GOTO :INPUT_START
 
:INPUT_CONF
ECHO +-------------------------------------------------------+
ECHO  入力した文字は[%INPUT_STR%]でよろしいですか？
ECHO （Y / N）
ECHO +-------------------------------------------------------+
SET CONF_SELECT=
SET /P CONF_SELECT=
 
IF "%CONF_SELECT%"== SET CONF_SELECT=Y
IF /I NOT "%CONF_SELECT%"=="Y"  GOTO :INPUT_START


ECHO +----githubにアップデートする処理を行います-------------+
ECHO +----コメントは-----------------------------------------+
ECHO +----[%INPUT_STR%]----+

@ECHO ON


cd %path2%
git fetch origin master
git reset --hard origin/master
del src\* /s /q
cd %path1%


xcopy * %path2%* /e
rmdir /s /q  %path2%\target
rmdir /s /q  %path2%\fig


cd %path2%
git add .
git commit -m %INPUT_STR%
git push origin master
explorer %path2%


cd %path1%
git fetch origin master
git reset --hard origin/master

@ECHO OFF

ECHO +-------------------終了しました------------------------+


:INPUT_END
ECHO +-------------------------------------------------------+
ECHO  完了しました。
ECHO +-------------------------------------------------------+
 
PAUSE
EXIT



