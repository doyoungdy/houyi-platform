@echo on
@echo =============================================================
@echo $                                                           $
@echo $               HXHEALTH Platform                           $
@echo $                                                           $
@echo $                                                           $
@echo $                                                           $
@echo $  HXHEALTH All Right Reserved                              $
@echo $  Copyright (C) 2019-2050                                  $
@echo $                                                           $
@echo =============================================================
@echo.
@echo off

@title HXHEALTH Platform
@color 0e

set /p version=请输入版本号:

call mvn versions:set -DnewVersion=%version%

call mvn versions:commit

pause