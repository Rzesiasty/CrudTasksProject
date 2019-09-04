call runcrud.bat
if %ERRORLEVEL% == 0 goto runbrowser
echo.
echo gradlew built went wrong
goto fail


:runbrowser
cd \
cd Program Files (x86)\Google\Chrome\Application
chrome --incognito "http://localhost:8080/crud/v1/task/getTasks"
if %ERRORLEVEL% == 0 goto end
echo.
echo loading website went wrong
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.