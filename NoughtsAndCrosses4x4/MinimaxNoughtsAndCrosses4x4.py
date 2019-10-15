# Minh Do - AI TH03206
# Noughts And Crosses 4x4 Alpha-Beta Search AI using Minimax Algorithm
# Alpha-Beta pruning implemented

import math
import random
from sys import exit
from collections import defaultdict

# Pygame module imported for computer graphics.
# Pygame is a popular API to make visuals easier.
import pygame
from pygame.locals import *
pygame.init()
# Framerate Constant (relatively unimportant for nature of the game)
FPS = 15

# Enum values for player/CPU
computer = 1
human = 2

# enum values for difficulty
easy = 49
medium = 3177
hard = 203374  # min nodes to exactly be unbeatable

# Enum values for spot locations
empty = 0
O = 3  # skipping 1 and 2 allows math shortcut for non-terminal node evaluation
X = 4

# Define difficulty and move
difficulty = ''
move = 1

# Color Shortcuts
black = (0, 0, 0)
white = (255, 255, 255)
red = (200, 0, 0)
blue = (0, 0, 200)


specialcolor = (164, 98, 98)  # this's welcome heading's color.
white = (255, 255, 255)
black = (0, 0, 0)  # _*_ coding: this's 1.0_Apple's color _*_
red = (255, 0, 0)
pink = (255, 0, 255)
DarkStateBlue = (72, 61, 139)
Yellow = (255, 255, 0)
Green = (0, 200, 0)
RedLightness50percent = (255, 77, 77)
Orrange = (255, 165, 0)
OrrangeRed = (255, 69, 0)  # _*_ coding: text's color for GAME OVER _*_
Gold = (250, 215, 0)  # _*_ coding: this's border's color of the notification at the end of the game _*_
Peru = (205, 133, 63)
DarkSeeGreen = (143, 188, 143)  # _*_ coding: this is 1.0_Apple's color _*_
PaleGreen = (152, 251, 152)
YellowGreen = (154, 205, 50)  # _*_ coding:this is border 's color of the notification in the end of the game _*_
DarkKhaki = (189, 183, 107)
CornFlowerBlue = (100, 149, 237)  # _*_coding: this is 0.5_Apple's color _*_
Tomato = (255, 99, 71)  # _*_ coding: this is 1.5_Apple's color _*_
LightGoldenRod = (238, 221, 130)
PaleGoldenRod = (238, 232, 170)  # _*_ coding: this is background's color _*_
RosyBrown = (188, 143, 143)  # _*_ coding: this is Snake's skin _*_
MidnightBlue = (25, 25, 122)
Azure = (240, 255, 255)
LemonChiffon = (255, 250, 205)
Lavender = (230, 230, 250)  # _*_ coding: this is text's color for the notification at the end of the game _*_
AquaMarine = (127, 255, 212)
DodgerBlue = (30, 144, 255)
CadetBlue = (95, 158, 160)  # this's message's color
SteelBlue = (70, 130, 180)
SandyBrown = (244, 164, 96)
SlateGray = (112, 128, 144)
MediumSeaGreen = (60, 179, 133)  # this's message's color
Salmon = (250, 128, 114)
Silver = (192, 192, 192)  # this 's first background 's color
ColorNotKnown = (255, 255, 204)
ColorGreenDarker = (5, 163, 114)
ColorGreenPaler = (128, 203, 92)
ColorSelfDefined1 = (23, 231, 161)
ColorSelfDefined2 = (253, 102, 80)

gameOverCk = False

display_width = 1200
display_height = 700
gameDisplay = pygame.display.set_mode((display_width, display_height))

mes = "Noughts And Crosses Version 1.0"
# mes = mes.encode("utf-8")
pygame.display.set_caption(mes)

clock = pygame.time.Clock()
# _*_ coding: set time and FPS _*_

borderfont = pygame.font.SysFont("twcenttb", 60, False, True)

specialfont = pygame.font.SysFont("Times New Roman", 55)
# font-family, font-size, bold=False, italic=False.
extrafont = pygame.font.SysFont("liberationmono", 90)
largefont = pygame.font.SysFont("liberationserif", 80)
smallfont = pygame.font.SysFont("liberationserif", 35)
mediumfont = pygame.font.SysFont("Times New Roman", 65)
smediumfont = pygame.font.SysFont("liberationserif", 60)
border = "-----------------------------------" * 2


def textWithObjects(text, color, size):
    if size == "small":
        textSuface = smallfont.render(text, True, color)
    elif size == "special":
        textSuface = specialfont.render(text, True, color)
    elif size == "medium":
        textSuface = mediumfont.render(text, True, color)
    elif size == "large":
        textSuface = largefont.render(text, True, color)
    elif size == "extra":
        textSuface = extrafont.render(text, True, color)
    elif size == "smedium":
        textSuface = smediumfont.render(text, True, color)
    return textSuface, textSuface.get_rect()

def messageIntoScreen(msg, color, distance_between_2_message=0, size="small"):
    textSurf, textRect = textWithObjects(msg, color, size)
    textRect.center = (display_width / 2), (display_height / 2) + distance_between_2_message
    gameDisplay.blit(textSurf, textRect)  # _*_ print the message into screen _*_
    # _*_ coding: gameDisplay.blit(screen_text, [display_width/11, display_height/2]) _*_
    # _*_ coding: screen_text = font.render(msg, True, color) _*_
    border1 = borderfont.render(border, True, Gold)
    # border3 = borderfont.render(border2, True, Gold)
    # gameDisplay.blit(border3, [(display_height/2)+6, display_width/8.7])
    # gameDisplay.blit(border3, [(display_height/2)+66, display_width/8.7])
    gameDisplay.blit(border1, [display_width / 10, (display_height / 2) + 6])
    gameDisplay.blit(border1, [display_width / 10, (display_height / 2) + 76])

def messageIntoScreen0(msg, color, distance_between_2_message=0, size="small"):
    textSurf, textRect = textWithObjects(msg, color, size)
    textRect.center = (display_width / 2), (display_height / 2) + distance_between_2_message
    gameDisplay.blit(textSurf, textRect)  # _*_ print the message into screen _*_


def messageIntoScreen1(msg, color, distance_between_2_message=0, size="small"):
    textSurf, textRect = textWithObjects(msg, color, size)
    textRect.center = display_width / 2.4, (display_height / 2) + distance_between_2_message
    gameDisplay.blit(textSurf, textRect)  # _*_ print the message into screen _*_

def messageIntoScreen2(msg, color, distance_between_2_message=0, size="small"):
    textSurf, textRect = textWithObjects(msg, color, size)
    textRect.center = (display_width / 2)+ 150, (display_height / 2) + distance_between_2_message
    gameDisplay.blit(textSurf, textRect)

def messageIntoScreenEm(msg, color, distance_between_2_message=0, size="small"):
    textSurf, textRect = textWithObjects(msg, color, size)
    textRect.center = (display_width / 2), (display_height / 2) + distance_between_2_message
    gameDisplay.blit(textSurf, textRect)  # _*_ print the message into screen _*_

def pause():
    paused = True

    gameDisplay = pygame.display.set_mode((display_width, display_height))
    gameDisplay.fill(black)
    messageIntoScreen("Paused", white, -100, size="extra")
    messageIntoScreen("Press C to continue and Q to quit the game.", white, 60, size="smedium")
    pygame.display.update()
    while paused:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()

            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_c:
                    paused = False
                elif event.key == pygame.K_q:
                    pygame.quit()
                    quit()
        clock.tick(10)

def playAgainOnClick(button):
        messageIntoScreen2("Nhấn vào màn hình", specialcolor, -145, size="special")
        messageIntoScreen2("này để chơi lại", specialcolor, -85, size="special")
        messageIntoScreen2("lần nữa.", specialcolor, -25, size="special")
        pygame.display.update()
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_a:
                    gameOverCk = True
# Base class for the players
class Player:
    # Player Init
    def __init__(self, type, symbol, name):
        self.type = type  # type of player
        self.symbol = symbol  # X or O
        self.name = name  # name

    # Board Setup
    def SetBoard(self, board):
        self.board = board

    # Move decision to be overridden
    def GetMove(self):
        pass

    # Process mouse click to be overridden
    def MouseClick(self, cell):
        pass

    # Get opponent symbol
    def OppositeSign(self, symbol):
        if symbol == O:
            return X
        return O


# Human class inherits from Player
class HumanPlayer(Player):
    # Human init
    def __init__(self, symbol, name):
        super().__init__(human, symbol, name)  # call Player constructor
        self.lastmove = -1

    # Waits until mouse click detected
    def GetMove(self):
        if(self.lastmove != -1):
            move = self.lastmove
            self.lastmove = -1
            return move

    # returns cell of mouse click
    def MouseClick(self, cell):
        if cell not in self.board.moves:  # check if cell already occupied
            self.lastmove = cell


# Computer class
class ComputerPlayer(Player):
    # Computer init
    def __init__(self, symbol, name, difficulty=hard):
        super().__init__(computer, symbol, name)  # call Player contructor
        self.lastmove = -1
        self.maxnodes = difficulty
        # primitives for statistics
        self.cutoff = False  # whether cutoff occurred
        # self.maxdepth = 0  # max depth reached
        self.currnodes = 0  # current number of nodes recursed
        self.maxprune = 0  # number of max prunings
        self.minprune = 0  # number of min prunings
        self.turn = 0


    # returns next computer move to make
    def GetMove(self):
        possiblemoves = [mv for mv in self.board.possiblecells if mv not in
        self.board.moves]  # determines possible moves

        # optimization hard coded as eval always chooses (0,0)
        if len(possiblemoves) == 16:
            self.turn += 1
            print("Static Optimized First Move.")
            return (0, 0)

        if len(possiblemoves) == 14 and (0, 1) in possiblemoves:
            self.turn += 1
            print("Static Optimized Second Move.")
            return (0, 1)
        elif len(possiblemoves) == 14:
            self.turn += 1
            print("Static Optimized Second Move.")
            return (0, 2)

        self.loop = 0  # number of possible move combinations

        # reset primitives for each move
        self.cutoff = False  # whether cutoff occurred
        maxdepth = 0  # max depth reached
        self.currnodes = 0  # current number of nodes recursed
        self.maxprune = 0  # number of max prunings
        self.minprune = 0  # number of min prunings

        # update turn
        self.turn += 1

        val, move, maxdepth = self.MaxValue(-1000, 1000)  # max utility via MaxValue func
        # print(str(self.loop) + "\tmoves")  # if you want to print move combos
        print("\nTurn", self.turn)
        if self.cutoff:
            print("Cutoff Reached")
        print("Maximum depth:", maxdepth)
        print("Number of nodes generated:", self.currnodes + 1)
        print("Number of max prunings:", self.maxprune)
        print("Number of min prunings:", self.minprune)
        return move

    # Determine value of terminal condition
    def GetScore(self):
        if self.board.Draw():
            return 0  # draw
        elif self.board.GetWinner() == self.symbol:
            return 1  # computer win
        return -1  # player win

    # returns maximum utility value
    def MaxValue(self, alpha, beta, height=0):
        self.currnodes += 1
        height += 1
        depth = height
        maxpos = None
        maxval = -1000  # utility value requested

        for move in self.board.getFreePositions():
            self.loop += 1  # increment possible move combinations
            self.board.Move(move, self.symbol)  # make corresponding move

            if self.currnodes >= self.maxnodes:
                self.cutoff = True
                playerlines1 = 0
                playerlines2 = 0
                playerlines3 = 0
                opponentlines1 = 0
                opponentlines2 = 0
                opponentlines3 = 0
                for line in self.board.alllines:
                    p1, p2, p3, p4 = line
                    val = self.board.board[p1] #+ self.board.board[p2] + self.board.board[p3] + self.board.board[p4]
                    val2 = self.board.board[p1] #+ self.board.board[p2] + self.board.board[p3] + self.board.board[p4]
                    for i in range(3):
                        if val == self.symbol * 3:
                            playerlines3 += 1
                            break
                        if val == self.symbol * 2:
                            playerlines2 += 1
                            break
                        if val == self.symbol:
                            playerlines1 += 1
                            break
                        val -= self.OppositeSign(self.symbol)

                    for i in range(3):
                        if val2 == self.OppositeSign(self.symbol) * 3:
                            opponentlines3 += 1
                            break
                        if val2 == self.OppositeSign(self.symbol) * 2:
                            opponentlines2 += 1
                            break
                        if val2 == self.OppositeSign(self.symbol):
                            opponentlines1 += 1
                            break
                        val2 -= self.symbol

                v = 6 * playerlines3 + 3 * playerlines2 + playerlines1\
                  - (6 * opponentlines3 + 3 * opponentlines2 + opponentlines1)
            else:
                if self.board.GameOver():  # evaluate terminal condition
                    v = self.GetScore()  # score of terminal condition
                else:  # switch to min choice
                    v, movepos, depth = self.MinValue(alpha, beta, height)  # call to min 'player'

            self.board.UndoMove()  # undo before decision made

            if v >= beta:
                return v, move, depth
            if v > alpha:
                alpha = v  # set alpha for next MinValue call

            if v > maxval:
                maxval = v  # set max possibile value
                maxpos = move  # set best move

            if v == 1:
                self.maxprune += 1
                break
        return maxval, maxpos, depth

    # returns minimum utility value
    def MinValue(self, alpha, beta, height=0):
        height += 1
        depth = height
        minpos = None
        minval = 1000  # utility value requested

        for move in self.board.getFreePositions():
            self.loop += 1  # increment possible move combinations
            self.board.Move(move, self.OppositeSign(self.symbol))  # make move

            if self.board.GameOver():  # evaluate terminal condition
                v = self.GetScore()  # score of terminal condition
            else:
                v, movepos, depth = self.MaxValue(alpha, beta, height)  # call to max 'player'

            self.board.UndoMove()  # undo before decision made

            if v < alpha:
                return v, move, depth
            if v < beta:
                beta = v  # set beta for next MaxValue call

            if v < minval:
                minval = v  # set min possible value
                minpos = move  # set best move
            if v == -1:
                self.minprune += 1
                break

        return minval, minpos, depth


# Noughts And Crosses board interface
class BackEndBoard:
    # all cell tuples
    possiblecells = [(a, b) for a in range(0, 4) for b in range(0, 4)]
    # all direct lines/possible wins
    alllines = [[(a, b) for a in range(0, 4)] for b in range(0, 4)] +\
               [[(b, a) for a in range(0, 4)] for b in range(0, 4)] +\
               [[(0, 0), (1, 1), (2, 2), (3, 3)],
               [(0, 3), (1, 2), (2, 1), (3, 0)]]

    # back end init
    def __init__(self):
        self.moves = []  # list of moves
        self.gameover = False  # gameover flag
        self.draw = False  # draw to screen flag
        self.board = defaultdict(lambda: empty)  # internal board

    # returns playable positions
    def getFreePositions(self):
        return [x for x in self.possiblecells if x not in self.moves]

    # creates a move with board location as position and player shape as symbol
    def Move(self, position, symbol):
        if self.board[position] != empty:
            return False  # non valid move
        self.board[position] = symbol  # set symbol
        self.moves.append(position)  # add move to list
        self.CheckGameOver()  # check gameover state
        return True  # valid move

    # undoes move to allow minimax evaluation
    def UndoMove(self):
        if len(self.moves) == 0:
            return False  # cannot undo if no moves made
        self.board[self.moves.pop()] = empty  # remove symbol from board
        self.gameover = False  # game cannot be over if undo is made
        return True  # undo completed

    def GameOver(self):
        return self.gameover  # gameover state

    def Draw(self):
        return self.draw  # return draw to screen flag

    # return winner of game
    def GetWinner(self):
        if self.GameOver() and not self.Draw():
            return self.winner  # winner

    # checks for game over possibility
    def CheckGameOver(self):
        for line in self.alllines:
            p1, p2, p3, p4 = line  # get each line coordinate
            # check if board at each position holds same symbol
            if self.board[p1] != empty and\
               self.board[p1] == self.board[p2]\
               == self.board[p3] == self.board[p4]:
                self.gameover = True  # true if equal
                self.winner = self.board[p1]  # set winner
                self.draw = False  # deny draw
                break
        else:
            if len(self.moves) == 16:
                self.draw = True  # draw if all moves made
                self.gameover = True  # end game
            else:
                self.gameover = False  # do not end


# Visual board class
class FrontBoard:
    # color numbers defined ealier
    gridcolor = black
    colorO = ColorSelfDefined2
    colorX = ColorSelfDefined1

    # front end init
    def __init__(self, boardsize=500):
        self.players = []  # player list
        self.boardsize = boardsize  # size of board
        self.gameboard = BackEndBoard()  # set backend for calculations
        self.font = pygame.font.Font(None, 50)  # default text/text size

    # resets and clears board
    def reset(self):
        self.gameboard = BackEndBoard()  # new backend
        for player in self.players:
            player.SetBoard(self.gameboard)  # set players boards
        self.player1, self.player2 = self.player2, self.player1  # change order

    # print winner/loser
    def printstatus(self, screen):
        checkNextTurn = False
        checkWinner = False
        textstr = ''  # output string
        if game.gameboard.GameOver():
            if game.gameboard.Draw():
                textstr = "Tỉ số hòa."
            else:
                button = pygame.Rect(550, 220, 400, 100)
                checkWinner = True
                textstr = self.player1.name
                playAgainOnClick(button)
        else:
            checkNextTurn = True
            textstr =  "Lượt tiếp theo là của"
            textstr1 = self.player1.name
        text = self.font.render(textstr, 1, black)  # render settings
        textpos = text.get_rect(centerx=screen.get_width() / 2,
                  y=self.boardsize - 5)  # text position
        messageIntoScreen2(textstr, black, -315, size="special")
        if checkNextTurn == True :
            messageIntoScreen2(textstr1, black, -255, size="special")
        if checkWinner == True :
            gameOverCk = True
            textstr1 = "đã dành chiến thắng."
            messageIntoScreen2(textstr1, black, -255, size="special")
        #screen.blit(text, textpos)  # text to screen
    # add players to self
    def AddPlayer(self, player):
        player.SetBoard(self.gameboard)  # set players board
        self.players.append(player)  # player list
        if(len(self.players) > 1):
            self.player1 = self.players[0]  # individual setting
            self.player2 = self.players[1]  # individual setting

    # actuall board drawing
    def draw(self, screen):
        tolerance = 2  # limit edges before edge of screen

        # drawing each line of the grid
        pygame.draw.line(screen, self.gridcolor,
                        (self.boardsize / 4 - 120, tolerance),
                        (self.boardsize / 4 - 120, self.boardsize - tolerance), 10)
        pygame.draw.line(screen, self.gridcolor,
                        (self.boardsize / 4, tolerance),
                        (self.boardsize / 4, self.boardsize - tolerance), 10)
        pygame.draw.line(screen, self.gridcolor,
                        ((2 * self.boardsize) / 4, tolerance),
                        ((2 * self.boardsize) / 4,
                        self.boardsize - tolerance), 10)
        pygame.draw.line(screen, self.gridcolor,
                        ((3 * self.boardsize) / 4, tolerance),
                        ((3 * self.boardsize) / 4,
                        self.boardsize - tolerance), 10)
        pygame.draw.line(screen, self.gridcolor,
                        (self.boardsize, 1),
                        (self.boardsize,
                        self.boardsize+5), 10)

        pygame.draw.line(screen, self.gridcolor,
                        (tolerance, (self.boardsize) / 4 - 120),
                        (self.boardsize,
                        (self.boardsize) / 4 - 120), 10)
        pygame.draw.line(screen, self.gridcolor,
                        (tolerance, (self.boardsize) / 4),
                        (self.boardsize - tolerance,
                        (self.boardsize) / 4), 10)
        pygame.draw.line(screen, self.gridcolor,
                        (tolerance, (2 * self.boardsize) / 4),
                        (self.boardsize - tolerance,
                        (2 * self.boardsize) / 4), 10)
        pygame.draw.line(screen, self.gridcolor,
                        (tolerance, (3 * self.boardsize) / 4),
                        (self.boardsize - tolerance,
                        (3 * self.boardsize) / 4), 10 )
        pygame.draw.line(screen, self.gridcolor,
                        (1, (self.boardsize)),
                        (self.boardsize,
                        self.boardsize), 10)
        

        # draw each X or O
        for move in self.gameboard.moves:
            mx, my = move  # move x and y coordinates
            quarter = int(self.boardsize / 4)  # 1/4 of board size

            if self.gameboard.board[move] == O:
                # draw a O in that position
                pos = mx * quarter + int(quarter / 2), my * quarter +\
                      int(quarter / 2)
                pygame.draw.circle(screen, self.colorO, pos,
                                   int(quarter / 4) + 10, 8)
            elif self.gameboard.board[move] == X:
                # draw an X in that position
                tl = mx * quarter + int(quarter / 5), my * quarter +\
                     int(quarter / 5)

                tr = (mx + 1) * quarter - int(quarter / 5),\
                     my * quarter + int(quarter / 5)

                bl = mx * quarter + int(quarter / 5),\
                     (my + 1) * quarter - int(quarter / 5)

                br = (mx + 1) * quarter - int(quarter / 5),\
                     (my + 1) * quarter - int(quarter / 5)

                pygame.draw.line(screen, self.colorX, tl, br, 10)
                pygame.draw.line(screen, self.colorX, tr, bl, 10)

    # find the position of the mouse click
    def MouseClick(self, position):
        mx, my = position  # x and y coordinates
        if mx < self.boardsize:  # if on board
            quarter = int(self.boardsize / 4)
            cx = int(math.floor(mx / quarter))
            cy = int(math.floor(my / quarter))
            cell = cx, cy  # convert to cell coordinates
            self.player1.MouseClick(cell)  # pass to player
        elif self.gameboard.GameOver():
            print("OK gameover.")
            self.reset()  # reset board

    # update board screen
    def update(self):
        if not self.gameboard.GameOver():
            nextpos = self.player1.GetMove()
            if nextpos is not None:
                # place players symbol
                self.gameboard.Move(nextpos, self.player1.symbol)
                if not self.gameboard.GameOver():
                    # switch players for next turn
                    self.player1, self.player2 = self.player2, self.player1

def gameIntroduction():
    intro = True
    
    while intro:

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_b:
                    intro = False
                
                # if event.key == pygame.K_q:
                #     pygame.quit()
                #     quit()
        gameDisplay.fill(ColorNotKnown)
        messageIntoScreen0("Cờ Caro cùng với AI", specialcolor, -310, "medium")
        messageIntoScreen0("(Sử dụng Minimax và Alpha-Belta Pruning)", specialcolor, -240, "medium")
        messageIntoScreenEm("Phiên bản 1.0 bởi @author Minh Do - 2019", RedLightness50percent, -180, "special")
        messageIntoScreen0("=======================Hướng Dẫn=======================", black, -110,
                           "medium")
        messageIntoScreen1("1.Người chơi sẽ chơi cờ caro với máy (Máy là X, người chơi là O).     ", MidnightBlue, -50, "small")
        messageIntoScreen1("2.Nhấn vào một ô trên bàn cờ để đi nước đi của mình.                         ", black, -5, "small")
        messageIntoScreen1("             3.Độ khó của trò chơi tương đương với số nút tối đa được tạo ra của Minimax.", Green, 40, "small")
        messageIntoScreen1("             4.Ví dụ với mức độ dễ, thường và khó, số nút được tạo ra tối đa sẽ là 49, 3177", CornFlowerBlue, 85, "small")
        messageIntoScreen1("và 203374.                                                                                     ", CornFlowerBlue, 130, "small")
        messageIntoScreen1("5.Người chơi sẽ được ưu tiên đi lượt đầu tiên.                                      ", MediumSeaGreen, 175, "small")
        messageIntoScreen1("6.Nhấn phím B để bắt đầu trò chơi và phím P để tạm dừng trò chơi.    ", CadetBlue, 220, "small")
        messageIntoScreen1("        7.Vì sử dụng Alpha-Belta Pruning nên lượt của máy có thể xảy ra chậm trễ.", Tomato, 265, "small")
        messageIntoScreen1("                      8.Người chiến thắng sẽ nhường lượt chơi đầu tiên của ván tiếp theo cho bên đối thủ.", specialcolor, 310, "small")
        pygame.display.update()
        clock.tick(10)

def level1():
    level1 = True
    checkMes2 = True
    checkMes1 = True
    while level1:

        #if(checkMes2 == True):
            #gameDisplay.fill(black)
            #messageIntoScreen0("Xin hãy chọn lượt chơi (1, 2):        ", CornFlowerBlue, -200, size="medium")
            #messageIntoScreen0("- LƯỢT ĐẦU : 1.     ", Peru, -80, size="large")
            #messageIntoScreen0("- LƯỢT SAU : 2.     ", SlateGray, 40, size="large")

        
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_d :
                    difficulty = 'easy'
                    level1 = False
                elif event.key == pygame.K_t :
                    difficulty = 'medium'
                    level1 = False
                elif event.key == pygame.K_k :
                    difficulty = 'hard'
                    level1 = False
                elif event.key == pygame.K_1:
                    move = 2
                    level1 = False
                elif event.key == pygame.K_2:
                    move = 1
                    level1 = False
                elif event.key == pygame.K_p:
                    pause()

        if(checkMes1 == True):
            gameDisplay.fill(black)
            messageIntoScreen0("Xin hãy chọn độ khó (dễ , thường, khó): ", DarkKhaki, -200, size="medium")
            messageIntoScreen0("- Nhấn D để chọn mức độ Dễ.                    ", PaleGreen, -100, size="smedium")
            messageIntoScreen0("- Nhấn T để chọn mức độ Thường.             ", RosyBrown, 0, size="smedium")
            messageIntoScreen0("- Nhấn K để chọn mức độ Khó.                  ", Tomato, 100, size="smedium")
                
        pygame.display.update()
        clock.tick(120)

# main function
if(__name__ == "__main__"):
    for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            
    gameIntroduction()
    level1()
    boardsize = 1000  # board size
    # screen display
    screen = pygame.display.set_mode((boardsize, boardsize - 495))
    #pygame.display.set_caption('Tic Tac Toe')  # toolbar name
    gameover = False  # gameover starts false
    clock = pygame.time.Clock()  # clock for FPS
    game = FrontBoard()  # creation of board

    if difficulty == "hard":
        if move == 1:
            game.AddPlayer(HumanPlayer(O, "Player"))  # player 1
            game.AddPlayer(ComputerPlayer(X, "Computer", hard))  # player 2
        else:
            game.AddPlayer(ComputerPlayer(X, "Computer", hard))  # player 1
            game.AddPlayer(HumanPlayer(O, "Player"))  # player 2
    elif difficulty == "medium":
        if move == 1:
            game.AddPlayer(HumanPlayer(O, "Player"))  # player 1
            game.AddPlayer(ComputerPlayer(X, "Computer", medium))  # player 2
        else:
            game.AddPlayer(ComputerPlayer(X, "Computer", medium))  # player 1
            game.AddPlayer(HumanPlayer(O, "Player"))  # player 2
    elif difficulty == "easy":
        if move == 1:
            game.AddPlayer(HumanPlayer(O, "Player"))  # player 1
            game.AddPlayer(ComputerPlayer(X, "Computer", easy))  # player 2
        else:
            game.AddPlayer(ComputerPlayer(X, "Computer", easy))  # player 1
            game.AddPlayer(HumanPlayer(O, "Player"))  # player 2
    else:
        if move == 1:
            game.AddPlayer(HumanPlayer(O, "Player"))  # player 1
            game.AddPlayer(ComputerPlayer(X, "Computer"))  # player 2
        else:
            game.AddPlayer(ComputerPlayer(X, "Computer"))  # player 1
            game.AddPlayer(HumanPlayer(O, "Player"))  # player 2
    while not gameover:
        clock.tick(FPS)  # frame settings
        screen.fill(white)  # screen background
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                exit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_p:
                    pause()
            if event.type == pygame.MOUSEBUTTONUP:
                print("OK up.")
                game.MouseClick(event.pos)  # mouse click event
        game.update()  # update game
        game.draw(screen)  # update display
        game.printstatus(screen)  # print prompt
        pygame.display.update()  # render display update
