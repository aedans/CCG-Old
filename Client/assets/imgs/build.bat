@echo off
cd cards
setlocal enabledelayedexpansion
for %%j in (*.*) do (
set filename=%%~nj
set filename=!filename:.=_!
set filename=!filename: =_!
if not "!filename!"=="%%~nj" ren "%%j" "!filename!%%~xj"
)