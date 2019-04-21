import math, pyperclip

def main():
    mymessage = 'Cenoonommstmme oo snnio. s s c' # Put the message in here
    mykey = 8 # U can change the key

    plaintext = decryptmessage(mykey,mymessage)

    print(plaintext + '|')

    pyperclip.copy(plaintext)

def decryptmessage(key,message):

    numofcols = math.ceil(len(message)/key)
    numofrows = key
    numofshadedboxes = (numofcols * numofrows)- len(message)

    plaintext = [''] * numofcols

    col = 0
    row = 0

    for sym in message:
        plaintext[col] += sym
        col += 1

        if (col == numofcols) or (col == numofcols -1 and row >= numofrows - numofshadedboxes):
            col = 0
            row += 1
    return ''.join(plaintext)

if __name__ == '__main__':
    main()
