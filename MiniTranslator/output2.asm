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
        z dd 0


segment code use32 class=code
start:
        push dword x
        push dword _sformat
        call [scanf]
        add esp, 4 * 2
        push dword y
        push dword _sformat
        call [scanf]
        add esp, 4 * 2
        mov eax, 0
        add eax, [x]
        sub eax, [y]
        mov dword [z], eax

        push dword [z]

        push dword _format
        call [printf]
        add esp, 4 * 2
