import pyperclip

def main():
    mymessage = "Common sense is not so common."            
    # Have to change the message which you want to encrypt or decrypt
    
    mykey = 8              
    # The key is the most important things, don't forget it, it's the number of columns or boxes

    ciphertext = encryptmessage(mykey,mymessage)

    print(ciphertext + '|')

    pyperclip.copy(ciphertext)

def encryptmessage(key,message):
             ciphertext = [''] * key

             for col in range(key):
                 pointer = col

                 while pointer<len(message):
                     ciphertext[col] += message[pointer]
                     pointer += key
             return ''.join(ciphertext)
if __name__ == '__main__':
    main()
