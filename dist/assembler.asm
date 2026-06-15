.MODEL LARGE
.386
.STACK 200h

.DATA

var dd ?
var dd ?
_3 dd 3.0
_4 dd 4.0
_5 dd 5.0

.CODE

MOV AX,@DATA
MOV DS,AX
MOV ES,AX

FLD null
FLD _5
FMUL
FSTP _@aux5
FLD _@aux5
FSTP var

MOV AX,4C00h
INT 21h

END

