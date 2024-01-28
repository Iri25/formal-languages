bits 32
global start
extern exit, printf, scanf
import exit msvcrt.dll
import printf msvcrt.dll
import scanf msvcrt.dll

segment data use32 class=data
        _format db "Print: %d", 10, 0
        _sformat db "%d", 0
        x dd 0
        y dd 0


segment code use32 class=code
start:
        mov eax, 0
        add eax, 0
        mov dword [x], eax
        mov eax, 0
        add eax, 1
        mov dword [y], eax
        mov eax, 0
        add eax, [x]
        add eax, 100
        mov dword [x], eax