# first, I have to download Python 3.7 in python.org
"""More over, I have to run this on IDLE after updating pyperclip.""" 
import pyperclip
# I have to update pyperclip through running Command Prompt as administator and typing:"pip install pyperclip" and it just take a few seconds 
key = input("Key is: ") #I have to copy the key and paste it to key value

mode = input("Mode is: ") #Mode should be 'decrypt' or 'encrypt'

LETTERS = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

translated = ''

message = input("Message is: ") #This have to fill in 

message = message.upper()

for symbol in message:
    if symbol in LETTERS:
        num = LETTERS.find(symbol)
        if mode == 'encrypt':
            num = num + key
        elif mode == 'decrypt':
            num = num - key
        if num >= len(LETTERS):
            num = num - len(LETTERS)
        elif num < 0:
            num = num + len(LETTERS)

        translated = translated + LETTERS[num]
    else:
        translated = translated + symbol

print(translated)

pyperclip.copy(translated)
