call gradlew build
if "%ERRORLEVEL%" == "0" goto script
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:script
call runcrud.bat
if "%ERRORLEVEL%" == "0" goto web
echo Cannot rename file
goto fail

:web
start "" http://localhost:8080/crud/v1/task/getTasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.