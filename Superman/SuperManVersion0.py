import pygame
import random
import time

pygame.init()

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

display_width = 1200
display_height = 700
gameDisplay = pygame.display.set_mode((display_width, display_height))

# image = pygame.image.load('SnakeHead.png')
# iconCDImage = pygame.image.load('icon_c.png')
# birdIcon = pygame.image.load('bird.png')
# humanIcon = pygame.image.load('h.png')
# mouseIcon = pygame.image.load('mouse.png')
# stormIcon = pygame.image.load('st.png')
# threeLeavesIcon = pygame.image.load('three-leaves.png')
# usbIcon = pygame.image.load('icon_usb.png')
basketBallIcon = pygame.image.load('icon_basket_ball.png') #icon
flyingManIcon = pygame.image.load('flying_man.png')
superManIcon = pygame.image.load('superman.png') #main character
runningManIcon = pygame.image.load('running_man.png') #level 1
icecreamIcon = pygame.image.load('icream.png') #level 1
bombIcon = pygame.image.load('bomb.png') #level 1
franceFriesIcon = pygame.image.load('france_fries.png') #level 1
tennisIcon = pygame.image.load('tennis.png') #level 1
sandwichIcon = pygame.image.load('sandwich.png') #level 1
squatIcon = pygame.image.load('squat.png') #level 1
beerIcon = pygame.image.load('beer.png') #level 1
pullUpIcon = pygame.image.load('pull_up.png') #level 1
shoesIcon = pygame.image.load('shoes.png') #level 1 
pushupIcon = pygame.image.load('pushup.png') #level 1
brickWallIcon = pygame.image.load('brick_wall_icon_0.png') #level 2
gameControllerIcon = pygame.image.load('game_controller.png') #level 1
badmintonEquipmentsIcon = pygame.image.load('badminton_equipments.png') #level 1
hamburgerIcon = pygame.image.load('hamburger.png') #level 1
weightLiftingIcon = pygame.image.load('weightlifting.png') #level 1
alcoholIcon = pygame.image.load('alcohol.png') #level 1
boxingIcon = pygame.image.load('boxing.png') #level 1
swimmingIcon = pygame.image.load('swimming.png') #level 1
pingPongIcon = pygame.image.load('ping-pong.png') #level 1
luckyCatIcon = pygame.image.load('luckyCat.png') #level 1
cyclingIcon = pygame.image.load('cycling.png') #level 1


pygame.display.set_caption('Super Man Against Obesity Version 1.0 by author @Meozz - Std99')
# icon_cloud = pygame.image.load('st.png')
pygame.display.set_icon(basketBallIcon)

clock = pygame.time.Clock()
# _*_ coding: set time and FPS _*_

block_size = 25
FPS = 15

direction = "right"

borderfont = pygame.font.SysFont("twcenttb", 60, False, True)

specialfont = pygame.font.SysFont("comicsansms", 45, False, True)
# font-family, font-size, bold=False, italic=False.
extrafont = pygame.font.SysFont("liberationmono", 90)
largefont = pygame.font.SysFont("liberationmono", 70)
smallfont = pygame.font.SysFont("liberationserif", 35)
mediumfont = pygame.font.SysFont("trebuchetms", 50)
smediumfont = pygame.font.SysFont("liberationserif", 40)
border = "-----------------------------------" * 2

def iconDefine(icon):
    if icon == icecreamIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Pls choose ur favourite flavor of Ice Cream:", white, -300, size="medium")
        messageIntoScreen0("--> Ur input calories are 201 within 100 grams of Ice Cream with 46 % fat.", red, -200, size="smedium")
        messageIntoScreen0("Press D to choose Dreyer's Ice Cream Sushi.", CornFlowerBlue, -100, size="small")
        messageIntoScreen0("Press C to choose Caramel Macchiato Ice Cream Pie.", PaleGreen, -50, size="small")
        messageIntoScreen0("Press S to choose Strawberry Ice Cream in Lemon Bowls.", YellowGreen, 0, size="small")
        messageIntoScreen0("Press B to choose Black Sesame fortune cookie cups with green tea Ice Cream.", MediumSeaGreen, 50, size="small")
        messageIntoScreen0("Press M to choose Dreyer's Mint Mocha Hot Chocolate.", SlateGray, 100, size="small")
        messageIntoScreen0("Press P to choose Pineapple Coconut Hot Rum Sundae.", Lavender, 150, size="small")
        messageIntoScreen0("Press W to choose Watermelon Ice Cream Bombe.", SandyBrown, 200, size="small")
        messageIntoScreen0("Press G to choose Warm Ginger Bananas with Chocolate Ice Cream.", OrrangeRed, 250, "small")
        messageIntoScreen0("Type 1 to choose Dreyer's Peanut Butter Ice Cream Pops.", CadetBlue, 300, "small")
        pygame.display.update()
        a = True
        while a:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_d or event.key == pygame.K_c or event.key == pygame.K_s or event.key == pygame.K_b or event.key == pygame.K_m or event.key == pygame.K_p or event.key == pygame.K_w or event.key == pygame.K_g:
                        a = False
            clock.tick(20)
    elif icon == franceFriesIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Pls choose ur favourite French Fries: ", white, -300, size="medium")
        messageIntoScreen0("--> Ur input calories are 156 in 1 orders of French Fries with 45 % fat.", red, -200, size="smedium")
        messageIntoScreen0("Press F to choose Fish & Chips.", CornFlowerBlue, -100, size="small")
        messageIntoScreen0("Press N to choose Nacho-style fries.", PaleGreen, -50, size="small")
        messageIntoScreen0("Press S to choose Sweet potato fries.", YellowGreen, 0, size="small")
        messageIntoScreen0("Press B to choose Blue cheese fries.", MediumSeaGreen, 50, size="small")
        messageIntoScreen0("Press G to choose Garlic fries.", SlateGray, 100, size="small")
        messageIntoScreen0("Press P to choose French fry hot dog( Corn Dog, but instead of cornbread, it's fries!)", Lavender, 150, size="small")
        messageIntoScreen0("Press M to choose McDonald's fries.", SandyBrown, 200, size="small")
        messageIntoScreen0("Press C to choose Chili cheese fries.", Azure, 250, size="small")
        messageIntoScreen0("Press D to choose Fries dipped in a milkshake.", Gold, 300, size="small")
        pygame.display.update()
        b = True
        while b:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_f or event.key == pygame.K_n or event.key == pygame.K_s or event.key == pygame.K_b or event.key == pygame.K_g or event.key == pygame.K_p or event.key == pygame.K_m or event.key == pygame.K_c or event.key == pygame.K_d:
                        b = False
    elif icon == sandwichIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Pls choose ur favourite Sandwich: ", white, -300, size="medium")
        messageIntoScreen0("--> There are 352 calories in 1 Ham and Cheese Sandwich with 39% fat.", red,
                           -200, size="smedium")
        messageIntoScreen0("Press B to choose Bacon Sandwich( UK ), often eaten with ketchup or brown sauce.", CornFlowerBlue, -100, size="small")
        messageIntoScreen0("Press T to choose Bagel Toast from Israel.", PaleGreen, -50, size="small")
        messageIntoScreen0("Type 1 to choose Baked Bean from US.", YellowGreen, 0, size="small")
        messageIntoScreen0("Press M to choose 'Banh Mi' from Vietnam.", MediumSeaGreen, 50, size="small")
        messageIntoScreen0("Press R to choose British Rail from UK.", SlateGray, 100, size="small")
        messageIntoScreen0("Press C to choose Carrozza from Italy.",
                           Lavender, 150, size="small")
        messageIntoScreen0("Type 2 to choose Bauru from Brazil.", SandyBrown, 200, size="small")
        messageIntoScreen0("Press J to choose Barros Jarpa from Chile, also called Ham and Cheese.", red, 250, size="small")
        messageIntoScreen0("Press K to choose Broodje kroket from The Netherlands.", Gold, 300, size="small")
        pygame.display.update()
        c = True
        while c:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_b or event.key == pygame.K_1 or event.key == pygame.K_2 or event.key == pygame.K_t or event.key == pygame.K_m or event.key == pygame.K_r or event.key == pygame.K_c or event.key == pygame.K_j or event.key == pygame.K_k:
                        c = False
    elif icon == shoesIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Press T to take a shoes.", white, -100, size="large")
        pygame.display.update()
        s = True
        while s:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_t :
                        s = False
    elif icon == tennisIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("So appreciate that u choose to play tennis. ", white, -300, size="medium")
        messageIntoScreen0("--> There are 3 advantages of playing tennis u must know:", LemonChiffon,
                           -200, size="smedium")
        messageIntoScreen0("First, it's physical advantages: it reduces ur risk of heart disease and diabetes.",
                           CornFlowerBlue, -100, size="small")
        messageIntoScreen0("It also helps u control ur weight, strengthen ur bones, improve ur mood.", red, -65, size="small")
        messageIntoScreen0("The fact is playing it everyweek can help u increase ur changes for living longer.", YellowGreen, -30, size="small")
        messageIntoScreen0("Second, it's mental & psychological benefits: reduces stress & maintaining ", PaleGreen, 5, size="small")
        messageIntoScreen0("cognitive abilities.", PaleGreen, 40, "small")
        messageIntoScreen0("It not only can help ur mental awareness, but also lower ur levels of depression.", Orrange, 75, size="small")
        messageIntoScreen0("It helps u portray a positive image & develop self-control, it generates new ",
                           Lavender, 110, size="small")
        messageIntoScreen0("connections in the brain.", Lavender, 145, "small")
        messageIntoScreen0("Third, it's a great way to meet people, hang arround with friends, widen ur ", SandyBrown, 180, size="small")
        messageIntoScreen0("social circles.", SandyBrown, 215, "small")
        messageIntoScreen0("And it also build ur networks, or playing doubles gives u the opportunity to ", AquaMarine, 250,
                           size="small")
        messageIntoScreen0("works teamskills. ==> This is ending, u press C to continue the game.", AquaMarine, 285, "small")
        # messageIntoScreen0("Press K to choose Broodje kroket from The Netherlands.", Gold, 300, size="small")
        pygame.display.update()
        d = True
        while d:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_c :
                        d = False
    elif icon == pushupIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Pls choose kind of push up u gonna do: ", white, -300, size="medium")
        messageIntoScreen0("--> It's the simplest way to start practicing as a street work-out athlete.", red, -200,
                           size="smedium")
        messageIntoScreen0("Press R to choose Regular push up : 5 sets of 300 reps with 60-90s break each sets.", CornFlowerBlue, -100, size="small")
        messageIntoScreen0("Press D to choose Diamond push up : 10 sets of 100 reps with the same break time.", PaleGreen, -50, size="small")
        messageIntoScreen0("Press P to choose Pseudo push up : 5 sets of 200 reps with the same break time.", YellowGreen, 0, size="small")
        messageIntoScreen0("Press A to choose Archer push up: 5 sets of 100 reps with the same break time.", MediumSeaGreen, 50, size="small")
        messageIntoScreen0("Press O to choose One hand push up: 5 sets of 100 reps with the same break time.", SlateGray, 100, size="small")
        messageIntoScreen0("Press H to choose Hand-Stand push up: 10 sets of 100 reps with the same break time.",
                           Lavender, 150, size="small")
        messageIntoScreen0("Press C to choose Hand-Clap push up: 10 sets of 100 reps with the same break time.", SandyBrown, 200, size="small")
        messageIntoScreen0("Press B to choose Burpees: 10 sets of 100 reps with the same break time.", Azure, 250, size="small")
        messageIntoScreen0("Press W to choose Wide push up: 10 sets of 200 reps with the same break time.", Gold, 300, size="small")
        pygame.display.update()
        e = True
        while e:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_r or event.key == pygame.K_d or event.key == pygame.K_p or event.key == pygame.K_a or event.key == pygame.K_o or event.key == pygame.K_h or event.key == pygame.K_c or event.key == pygame.K_b or event.key == pygame.K_w:
                        e = False
    elif icon == beerIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Pls choose ur favourite Beer styles: ", white, -300, size="medium")
        messageIntoScreen0("--> Beer has 266 calories per 1.5 Oz, which converted to ml is 44.36 ml.", red, -220, size="smedium")
        messageIntoScreen0("Press F to choose Fruit Beer.                                                             ", CornFlowerBlue, -140, size="small")
        messageIntoScreen0("Press H to choose Herb and spiced Beer.                                          ", PaleGreen, -100, size="small")
        messageIntoScreen0("Press S to choose Smoked Beer.                                                        ", YellowGreen, -60, size="small")
        messageIntoScreen0("Press 1 to choose Honey Beer.                                                          ", MediumSeaGreen, -20, size="small")
        messageIntoScreen0("Press V to choose Vegetable Beer.                                                     ", Orrange, 20, size="small")
        messageIntoScreen0("Press R to choose Rye Beer (Black wheat), also called Black Beer.  ", CadetBlue, 60, size="small")
        messageIntoScreen0("Press W to choose Wild Beer.                                                           ", SandyBrown, 100, size="small")
        messageIntoScreen0("Press A to choose Wood-aged Beer.                                                   ", DarkSeeGreen, 140, size="small")
        messageIntoScreen0("Possible long-term health risk of large alcohol consumption include:                 ", DodgerBlue, 180, size="small")
        messageIntoScreen0("depression, cancer, impaired brain dev or memory, sleep disorders,                    ", Gold, 220, "small")
        messageIntoScreen0("hepatitis(liver inflammation), ataxia(loss of control of body movements)          ", Gold, 255, "small")
        messageIntoScreen0("chronic gastritis(stummy or stomach inflamation), anemia(lack of red blood cells", Gold, 290, "small")
        messageIntoScreen0("or hemoglobin in the blood), increased risk of Type 2 diabetes.                            ", Gold, 325, "small")
        pygame.display.update()
        f = True
        while f:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_f or event.key == pygame.K_h or event.key == pygame.K_s or event.key == pygame.K_1 or event.key == pygame.K_v or event.key == pygame.K_r or event.key == pygame.K_w or event.key == pygame.K_a :
                        f = False
    elif icon == runningManIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Running is the simplest way to lose ur weight.", white, -300, size="medium")
        messageIntoScreen0(", it also good for ur healh.                      ", white, -240, "medium")
        messageIntoScreen0("--> It can help u burn a great deal of calories too.     ", red, -190,
                           size="medium")
        messageIntoScreen0("The advantages of running is:                      ", CornFlowerBlue, -140, size="medium")
        messageIntoScreen0("The first one is running can raise ur level of good cholesterol, can lower ur risk of", PaleGreen, -90, size="small")
        messageIntoScreen0("developing blood clots, it also helps u increase lung function and use.                  ", PaleGreen, -55, size="small")
        messageIntoScreen0("In addition, it can also boost ur immune system, helps u strengthen ur heart.            ", MediumSeaGreen, -20, size="small")
        messageIntoScreen0("It prevents disease, ex: the risk of having stroke, heart attack, etc.                   ", DodgerBlue, 15, size="small")
        messageIntoScreen0("It help u lose weight: it's a leading way to burn off extra calories.                    ", Orrange, 50,
                           size="small")
        messageIntoScreen0("And that it's the second most effective exercise in terms of calories burned per         ", SandyBrown, 85, size="small")
        messageIntoScreen0("minutes, following only after cross country skiing.                                      ", SandyBrown, 120, size="small")
        messageIntoScreen0("Running can improve ur mood, make u more happier, it also provide an                     ", Gold, 155,
                           size="small")
        messageIntoScreen0("noticeable boost to ur confidence and self-esteem, relieves stress,                      ", Gold, 190,
                           "small")
        messageIntoScreen0("diminish appetite and sleep quality, it also has power to eliminate depression.          ", Gold, 225, "small")
        messageIntoScreen0("It's incredibly benefical to the body, mind & spirit, leave u more focused, energized.", DarkStateBlue, 260, "small")
        messageIntoScreen0(">>>The end<<< Press C to continue the game.", PaleGoldenRod, 295, "small")
        pygame.display.update()
        g = True
        while g:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_c :
                        g = False
    elif icon == pullUpIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Pls choose ur type of Pull Up u gonna do: ", white, -300, size="medium")
        messageIntoScreen0("--> There are some kind of Pull Up basic and advance be presented:", red, -240,
                           size="smedium")
        messageIntoScreen0("Press C to choose Chin Up: 10 sets of 100 reps with 60-120s break each set.", CornFlowerBlue, -200, size="small")
        messageIntoScreen0("Press R to choose Regular Pull Up : with same with above exer.", PaleGreen, -160, size="small")
        messageIntoScreen0("Press W to choose Wide Pull-Up or Chin-Up: with same with above exer.", YellowGreen, -120, size="small")
        messageIntoScreen0("Press A to choose Archer Pull-Up or Chin-Up: 10 sets of 50 reps.", MediumSeaGreen, -80, size="small")
        messageIntoScreen0("Press S to choose Side-to-side Pull-Up or Chin-Up: ______________", Silver, -40, size="small")
        messageIntoScreen0("Press O to choose One hand Assisted Pull-Up or Chin-Up:_________________",
                           PaleGoldenRod, 0, size="small")
        messageIntoScreen0("Press U to choose Upside Down Pull Up or Chin-Up:________________", SandyBrown, 40, size="small")
        messageIntoScreen0("Press 1 to choose Clap Pull-Up or Chin-Up:_________________.", Orrange, 80, size="small")
        messageIntoScreen0("Press X to choose The X Grip Pull-Up or Chin-Up:_________________.", Gold, 120, size="small")
        messageIntoScreen0("Press L to choose L-Shaped Pull-Up or Chin-Up: 10 sets of 100 reps.", DodgerBlue, 160, "small")
        messageIntoScreen0("Press M to choose Muscle Up --> Advance Pull Up: 5 sets of 50 reps.", DarkSeeGreen, 200, "small")
        messageIntoScreen0("Press 2 to choose Close Grip Pull-Up or Chin-Up: 10 sets of 100 reps.", Salmon, 240, "small")
        messageIntoScreen0("Press G to choose Gorilla Pull-Up or Chin-Up :10 sets of 200 reps.",CadetBlue, 280, "small")
        messageIntoScreen0("Press B to choose Behind The Head Pull-Up or Chin-Up: 10 sets of 50 reps.", SlateGray, 320, "small")
        pygame.display.update()
        h = True
        while h:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_c or event.key == pygame.K_r or event.key == pygame.K_2 or event.key == pygame.K_m or event.key == pygame.K_s or event.key == pygame.K_x or event.key == pygame.K_1 or event.key == pygame.K_u or event.key == pygame.K_o or event.key == pygame.K_l or event.key == pygame.K_a or event.key == pygame.K_w or event.key == pygame.K_g or event.key == pygame.K_b:
                        h = False
    elif icon == squatIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Squat is an exercise that good for health.", white, -300, size="medium")
        messageIntoScreen0("--> Keep ur chest up, and keep ur hips lower than the top of ur knee.", red, -220, size="smedium")
        messageIntoScreen0("Pls choose ur suitable kind of squat movement:", CornFlowerBlue, -140, size="small")
        messageIntoScreen0("Press F to choose Front Squat --> Advantages: balanced, core and upper", PaleGreen, -100, size="small")
        messageIntoScreen0("back strength, harder to cheat.It's also a crucial component of Olympic lifts.", PaleGreen, -60, size="small")
        messageIntoScreen0("Press O to choose Overhead Squat --> Advantages: balanced, muscular control,", MediumSeaGreen, -20, size="small")
        messageIntoScreen0("increased mobility, it strengthen the midpoint of the barbell snath and is", MediumSeaGreen, 20, size="small")
        messageIntoScreen0("essential to mastering that particular lift.", MediumSeaGreen, 60, size="small")
        messageIntoScreen0("Press Z to choose Zercher Squat --> Advantages: Torso and core strength, less", SandyBrown, 100, size="small")
        messageIntoScreen0("spinal compression, carryover to deadlift, it's the one of the most movement", SandyBrown, 140, size="small")
        messageIntoScreen0("for developing a strong upper back and torso.", SandyBrown, 180, size="small")
        messageIntoScreen0("Press L to choose One-Legged Squat --> Advantages: balanced, mobility,", Gold, 220, "small")
        messageIntoScreen0("high-tension strength.It's also called pistol squat, when mastered, it's exellent", Gold, 255, "small")
        messageIntoScreen0("way to build strength throughout the lower body.", Gold, 290, "small")
        # messageIntoScreen0("or hemoglobin in the blood), increased risk of Type 2 diabetes.                            ", Gold, 325, "small")
        pygame.display.update()
        i = True
        while i:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_f or event.key == pygame.K_o or event.key == pygame.K_z or event.key == pygame.K_l:
                        i = False
    elif icon == swimmingIcon:
        gameDisplay.fill(black)
        messageIntoScreen0("Swimming is a healthy activity that u can go on for a lifetime.", white, -300, size="medium")
        messageIntoScreen0("It's also a low-impact activity that has many physical & mental health benefits.", PaleGreen, -220, size="smedium")
        messageIntoScreen0("It's a great work-out cos u need to move whole ur body against the resistance", CornFlowerBlue, -160, size="small")
        messageIntoScreen0("of the water, it has some advantages: 1. Keeps ur heart rate up but takes some", CornFlowerBlue, -120, size="small")
        messageIntoScreen0("of the impact stress off ur body.", CornFlowerBlue, -80, size="small")
        messageIntoScreen0("2.It build endurance, muscle strength, it also help maintain a healthy weight,", MediumSeaGreen, -40, size="small")
        messageIntoScreen0(", healthy heart and lungs, tones muscles and provides an all-over body work-out,", MediumSeaGreen, 0, size="small")
        messageIntoScreen0("as nearly all of muscles are used during swimming.", MediumSeaGreen, 40, size="small")
        messageIntoScreen0("3.It alleviating stress, improving coordination, balance and posture, also", SandyBrown, 80, size="small")
        messageIntoScreen0("improving flexibility, provides a pleasant way to cool down on a hot day.", SandyBrown, 120, size="small")
        messageIntoScreen0("Pls choose ur kind of swimming u wanna go:", Gold, 160, size="small")
        messageIntoScreen0("Press F to choose Front Crawl (also called Free-Style).", MidnightBlue, 200, "small")
        messageIntoScreen0("Press B to choose Backstroke( Float on ur back in the water.", PaleGreen, 240, "small")
        messageIntoScreen0("Press U to choose Butterfly( Uses a dolphin style kick, ur legs'll stay straight.", CadetBlue, 280, "small")
        messageIntoScreen0("Press 1 to choose Breaststroke( Uses a Frog-Style kick, where u bend ur knees &", RosyBrown, 320, "small")
        messageIntoScreen0("then kick ur legs out beneath the water....Press S to choose Sidestroke(to rescue)", DodgerBlue, 360, "small")
        pygame.display.update()
        k = True
        while k:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_f or event.key == pygame.K_n or event.key == pygame.K_s or event.key == pygame.K_b or event.key == pygame.K_g or event.key == pygame.K_p or event.key == pygame.K_m or event.key == pygame.K_c or event.key == pygame.K_d:
                        k = False
# border2 = "----"

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


def messageIntoScreenEm(msg, color, distance_between_2_message=0, size="small"):
    textSurf, textRect = textWithObjects(msg, color, size)
    textRect.center = (display_width / 2), (display_height / 2) + distance_between_2_message
    gameDisplay.blit(textSurf, textRect)  # _*_ print the message into screen _*_

def navigateHuman(block_size, human_list):
    if direction == "right":
        head = superManIcon
    if direction == "left":
        head = pygame.transform.rotate(superManIcon, 180)
    if direction == "down":
        head = pygame.transform.rotate(superManIcon, 270)
    if direction == "up":
        head = pygame.transform.rotate(superManIcon, 90)

    gameDisplay.blit(head, (human_list[-1][0], human_list[-1][1]))

    # for ele in snake_list[:-1]:
    #     pygame.draw.rect(gameDisplay, RosyBrown, [ele[0], ele[1], block_size, block_size])

def score( score):
    text = smallfont.render("Score: " + str(score), True, black)
    gameDisplay.blit(text, [0, 0])

def pause():
    paused = True

    messageIntoScreen("Paused", black, -100, size="extra")
    messageIntoScreen("Press C to continue and Q to quit the game.", black, 60, size="smedium")
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

def level1():
    level1 = True

    while level1:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_b:
                    level1 = False
        gameDisplay.fill(black)
        messageIntoScreen0("Level 1 begin!", white, -100, size="extra")
        messageIntoScreen0("Press B to begin right now.", white, 60, size="smedium")
        pygame.display.update()
        clock.tick(120)


def level2():
    level2 = True

    while level2:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_s:
                    gameLoopLevel2()
                    pygame.display.update()
        gameDisplay.fill(black)
        messageIntoScreen0("Level 2: Brick wall.", white, -100, size="extra")
        messageIntoScreen0("Press S to start game at level 2 right now.", white, 60, size="smedium")
        pygame.display.update()
        clock.tick(120)

def gameLoopLevel2():
    human_list = []
    scores = 1

    crashed = False
    game_over = False

    global direction  # _*_ coding: this is made reference the direction _*_
    lead_x = display_width / 8
    lead_y = display_height / 5
    lead_x_change = 0
    lead_y_change = 0

    Apple_thickness = 40
    randomAppleX = round(random.randrange(0, display_width - Apple_thickness))  # /10.0)*10.0
    randomAppleY = round(random.randrange(0, display_height - Apple_thickness))  # /10.0)*10.0

    randomAppleA = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
    randomAppleB = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

    randomAppleC = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomAppleD = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomAppleE = round(random.randrange(0, display_width - Apple_thickness))#/ 10.0) * 10.0
    randomAppleF = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

    randomAppleG = round(random.randrange(0, display_width - Apple_thickness))#/ 10.0) * 10.0
    randomAppleH = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

    randomAppleI = round(random.randrange(0, display_width - Apple_thickness))#/ 10.0) * 10.0
    randomAppleK = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

    randomAppleL = round(random.randrange(0, display_width - Apple_thickness))#/ 10.0) * 10.0
    randomAppleM = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

    randomAppleN = round(random.randrange(0, display_width - Apple_thickness))#/ 10.0) * 10.0
    randomAppleO = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

    while not crashed:
        while game_over == True:
            gameDisplay.fill(black)
            messageIntoScreen("GAME OVER", OrrangeRed, -60, size="extra")
            messageIntoScreen("  Pls press C to continue or Q to quit the game.", Lavender, 60, size="special")
            pygame.display.update()

            for event in pygame.event.get():
                if event.type == pygame.QUIT:  # _*_ coding: When user hit the X button on the top left of the screen. _*_
                    crashed = True
                    game_over = False
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_q:
                        crashed = True
                        game_over = False
                    if event.key == pygame.K_c:
                        gameLoop()

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                crashed = True
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    direction = "left"
                    lead_x_change = -block_size
                    lead_y_change = 0
                elif event.key == pygame.K_RIGHT:
                    direction = "right"
                    lead_x_change = block_size
                    lead_y_change = 0
                elif event.key == pygame.K_UP:
                    direction = "up"
                    lead_y_change = -block_size
                    lead_x_change = 0
                elif event.key == pygame.K_DOWN:
                    direction = "down"
                    lead_y_change = block_size
                    lead_x_change = 0
                elif event.key == pygame.K_p:
                    pause()
            # if event.type == pygame.KEYUP:
            #     lead_x_change = 0
            #     lead_y_change = 0

        # _*_ coding: the code below is specified the case that the snake is go out of the screen's border _*_
        if lead_x >= display_width or lead_x < 0 or lead_y >= display_height or lead_y < 0:
            game_over = True

        lead_x += lead_x_change
        lead_y += lead_y_change
        gameDisplay.fill(PaleGoldenRod)
        gameDisplay.blit(brickWallIcon, [350, -80])
        gameDisplay.blit(shoesIcon, (randomAppleN, randomAppleO)) # compulsory equipment for sports
        gameDisplay.blit(gameControllerIcon, (randomAppleX, randomAppleY)) # unhealthy
        gameDisplay.blit(hamburgerIcon, (randomAppleC, randomAppleD)) #unhealthy
        gameDisplay.blit(weightLiftingIcon, (randomAppleA, randomAppleB)) #healthy
        gameDisplay.blit(pushupIcon, (randomAppleE, randomAppleF)) #healthy
        gameDisplay.blit(alcoholIcon, (randomAppleG, randomAppleH)) #unhealthy
        gameDisplay.blit(swimmingIcon, (randomAppleI, randomAppleK)) #healthy
        gameDisplay.blit(badmintonEquipmentsIcon, (randomAppleL, randomAppleM)) #healthy

        human = []
        human.append(lead_x)
        human.append(lead_y)
        human_list.append(human)

        checkShoes = False
        # if len(snake_list) > snake_length:
        #     del snake_list[0]

        # _*_ coding: The code below is defined for the snake turn arround and touch its body and go die. _*_
        # for eachSegment in snake_list[:-1]:
        #     if eachSegment == snake_head:
        #         game_over = True

        navigateHuman(block_size, human_list)

        score(scores - 1)
        pygame.display.update()

        if randomAppleX >= 350 and randomAppleX <= 700:
            if randomAppleY >= 180 and randomAppleY <= 300:
                randomAppleX = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleY = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

        if randomAppleA >= 350 and randomAppleA <= 700:
            if randomAppleB >= 180 and randomAppleB <= 300:
                randomAppleA = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleB = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

        if randomAppleE >= 350 and randomAppleE <= 700:
            if randomAppleF >= 180 and randomAppleF <= 300:
                randomAppleE = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleF = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

        if randomAppleG >= 350 and randomAppleG <= 700:
            if randomAppleH >= 180 and randomAppleH <= 300:
                randomAppleG = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleH = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

        if randomAppleI >= 350 and randomAppleI <= 700:
            if randomAppleK >= 180 and randomAppleK <= 300:
                randomAppleI = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleK = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

        if randomAppleL >= 350 and randomAppleL <= 700:
            if randomAppleM >= 180 and randomAppleM <= 300:
                randomAppleL = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleM = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

        if randomAppleC >= 350 and randomAppleX <= 700:
            if randomAppleD >= 180 and randomAppleY <= 300:
                randomAppleC = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleD = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

        if randomAppleN >= 350 and randomAppleN <= 700:
            if randomAppleO >= 180 and randomAppleO <= 300:
                randomAppleN = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleO = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0

        if lead_x >= randomAppleN and lead_x <= randomAppleN + Apple_thickness or lead_x + block_size >= randomAppleN and lead_x + block_size <= randomAppleN + Apple_thickness:
            if lead_y >= randomAppleO and lead_y <= randomAppleO + Apple_thickness or lead_x + block_size >= randomAppleO and lead_x + block_size <= randomAppleO + Apple_thickness:
                randomAppleN = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
                randomAppleO = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0
                checkShoes = True

        if lead_x >= randomAppleX and lead_x <= randomAppleX + Apple_thickness or lead_x + block_size >= randomAppleX and lead_x + block_size <= randomAppleX + Apple_thickness:
            if lead_y >= randomAppleY and lead_y <= randomAppleY + Apple_thickness or lead_x + block_size >= randomAppleY and lead_x + block_size <= randomAppleY + Apple_thickness:
                randomAppleX = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleY = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0
                scores -= 2

        if lead_x >= randomAppleC and lead_x <= randomAppleC + Apple_thickness or lead_x + block_size >= randomAppleC and lead_x + block_size <= randomAppleC + Apple_thickness:
            if lead_y >= randomAppleD and lead_y <= randomAppleD + Apple_thickness or lead_x + block_size >= randomAppleD and lead_x + block_size <= randomAppleD + Apple_thickness:
                randomAppleC = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleD = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0
                scores -= 1

        if lead_x >= randomAppleL and lead_x <= randomAppleL + Apple_thickness or lead_x + block_size >= randomAppleL and lead_x + block_size <= randomAppleL + Apple_thickness:
            if lead_y >= randomAppleM and lead_y <= randomAppleM + Apple_thickness or lead_x + block_size >= randomAppleM and lead_x + block_size <= randomAppleM + Apple_thickness:
                randomAppleA = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleB = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0
                if(checkShoes==True):
                    scores += 2
                else:
                    gameDisplay.fill(black)
                    messageIntoScreen0("U have to take shoes first. Pls take it then play badminton.", white, -60, size="smedium")

        if lead_x >= randomAppleE and lead_x <= randomAppleE + Apple_thickness or lead_x + block_size >= randomAppleE and lead_x + block_size <= randomAppleE + Apple_thickness:
            if lead_y >= randomAppleF and lead_y <= randomAppleF + Apple_thickness or lead_x + block_size >= randomAppleF and lead_x + block_size <= randomAppleF + Apple_thickness:
                randomAppleE = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleF = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0
                scores += 2

        if lead_x >= randomAppleG and lead_x <= randomAppleG + Apple_thickness or lead_x + block_size >= randomAppleG and lead_x + block_size <= randomAppleG + Apple_thickness:
            if lead_y >= randomAppleH and lead_y <= randomAppleH + Apple_thickness or lead_x + block_size >= randomAppleH and lead_x + block_size <= randomAppleH + Apple_thickness:
                randomAppleG = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleH = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0
                scores -= 3

        if lead_x >= randomAppleI and lead_x <= randomAppleI + Apple_thickness or lead_x + block_size >= randomAppleI and lead_x + block_size <= randomAppleI + Apple_thickness:
            if lead_y >= randomAppleK and lead_y <= randomAppleK + Apple_thickness or lead_x + block_size >= randomAppleK and lead_x + block_size <= randomAppleK + Apple_thickness:
                randomAppleC = round(random.randrange(0, display_width - Apple_thickness) / 10.0) * 10.0
                randomAppleD = round(random.randrange(0, display_height - Apple_thickness) / 10.0) * 10.0
                scores += 2
                iconDefine(swimmingIcon)

        if lead_x >= randomAppleA and lead_x <= randomAppleA + Apple_thickness or lead_x + block_size >= randomAppleA and lead_x + block_size <= randomAppleA + Apple_thickness:
            if lead_y >= randomAppleB and lead_y <= randomAppleB + Apple_thickness or lead_x + block_size >= randomAppleB and lead_x + block_size <= randomAppleB + Apple_thickness:
                randomAppleC = round(random.randrange(0, display_width - Apple_thickness))# / 10.0) * 10.0
                randomAppleD = round(random.randrange(0, display_height - Apple_thickness))# / 10.0) * 10.0
                scores += 2

        # if lead_x >= 350 and lead_x <= (494/2)  or lead_x + (494/2) >= 350 and lead_x +(494/2) < 350 +(494/2):
        #     if lead_y >= -80 and lead_y <= -80  or lead_x + (832/2) >= -80 and lead_x + (832/2) < -80 + (832/2):
        if lead_x >= 350 and lead_x <= 700:
            if lead_y >= 180 and lead_y <= 300:
                game_over = True
                pygame.display.update()
        clock.tick(FPS)
        # _*_ coding: it's 20 frame per second _*_
    time.sleep(0.01)
    pygame.quit()
    quit()


def gameLoop():
    human_list = []
    scores = 1

    crashed = False
    game_over = False

    global direction  # _*_ coding: this is made reference the direction _*_
    lead_x = display_width / 2
    lead_y = display_height / 2
    lead_x_change = 0
    lead_y_change = 0
    Apple_thickness = 40

    randomAppleX = round(random.randrange(0, display_width - Apple_thickness))  # /10.0)*10.0
    randomAppleY = round(random.randrange(0, display_height - Apple_thickness))  # /10.0)*10.0

    randomAppleA = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomAppleB = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomAppleC = round(random.randrange(0, display_width - Apple_thickness))  # /10.0)*10.0
    randomAppleD = round(random.randrange(0, display_height - Apple_thickness))  # /10.0)*10.0

    randomAppleE = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomAppleF = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomAppleG = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomAppleH = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomAppleI = round(random.randrange(10, display_width - Apple_thickness))#/ 10.0) * 10.0
    randomAppleJ = round(random.randrange(10, display_height - Apple_thickness))# / 10.0) * 10.0

    randomAppleK = round(random.randrange(5, display_width - Apple_thickness))#/ 10.0) * 10.0
    randomAppleL = round(random.randrange(5, display_height - Apple_thickness))# / 10.0) * 10.0

    randomAppleM = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomAppleN = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomAppleO = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomAppleP = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomAppleQ = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomAppleR = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple1 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple2 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple3 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple4 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple5 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple6 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple7 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple8 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple9 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple0 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple01 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple02 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple03 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple04 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple05 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple06 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0

    randomApple07 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple08 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0
    
    randomApple09 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple10 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0
    
    randomApple11 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple12 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0
    
    randomApple13 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple14 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0
    
    randomApple15 = round(random.randrange(5, display_width - Apple_thickness))  # / 10.0) * 10.0
    randomApple16 = round(random.randrange(5, display_height - Apple_thickness))  # / 10.0) * 10.0


    while not crashed:
        while game_over == True:
            gameDisplay.fill(black)
            messageIntoScreen("GAME OVER", OrrangeRed, -60, size="extra")
            messageIntoScreen("  Pls press C to continue or Q to quit the game.", Lavender, 60, size="special")
            pygame.display.update()

            for event in pygame.event.get():
                if event.type == pygame.QUIT:  # _*_ coding: When user hit the X button on the top left of the screen. _*_
                    crashed = True
                    game_over = False
                if event.type == pygame.KEYDOWN:
                    if event.key == pygame.K_q:
                        crashed = True
                        game_over = False
                    if event.key == pygame.K_c:
                        gameLoop()

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                crashed = True
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_LEFT:
                    direction = "left"
                    lead_x_change = -block_size
                    lead_y_change = 0
                elif event.key == pygame.K_RIGHT:
                    direction = "right"
                    lead_x_change = block_size
                    lead_y_change = 0
                elif event.key == pygame.K_UP:
                    direction = "up"
                    lead_y_change = -block_size
                    lead_x_change = 0
                elif event.key == pygame.K_DOWN:
                    direction = "down"
                    lead_y_change = block_size
                    lead_x_change = 0
                elif event.key == pygame.K_p:
                    pause()
            # if event.type == pygame.KEYUP:
            #     lead_x_change = 0
            #     lead_y_change = 0

        # _*_ coding: the code below is specified the case that the running man is go out of the screen's border _*_
        if lead_x >= display_width or lead_x < 0 or lead_y >= display_height or lead_y < 0:
            game_over = True

        lead_x += lead_x_change
        lead_y += lead_y_change
        gameDisplay.fill(PaleGoldenRod)  # _*_ coding: set the background color _*_

        # pygame.draw.rect(gameDisplay, DarkSeeGreen, [randomAppleX, randomAppleY, Apple_thickness, Apple_thickness])
        # pygame.draw.rect(gameDisplay, black, [randomAppleA, randomAppleB, Apple_thickness, Apple_thickness])
        # pygame.draw.rect(gameDisplay, Tomato, [randomAppleC, randomAppleD, 1.5*Apple_thickness, 1.5*Apple_thickness])
        # pygame.draw.rect(gameDisplay, CornFlowerBlue, [randomAppleE, randomAppleF, Apple_thickness/2, Apple_thickness/2])
        # gameDisplay.blit(humanIcon, (randomAppleE, randomAppleF))
        # gameDisplay.blit(cloudIcon, (randomAppleE, randomAppleF))
        # gameDisplay.blit(birdIcon, (randomAppleG, randomAppleH))
        # gameDisplay.blit(stormIcon, (randomAppleX, randomAppleF))
        # gameDisplay.blit(brickWallIcon,[350, -80])
        gameDisplay.blit(franceFriesIcon, (randomAppleE, randomAppleF))
        gameDisplay.blit(bombIcon, (randomAppleC, randomAppleD))
        gameDisplay.blit(sandwichIcon, (randomAppleX, randomAppleY))
        gameDisplay.blit(runningManIcon, (randomAppleA, randomAppleB))
        gameDisplay.blit(beerIcon, (randomAppleG, randomAppleH))
        gameDisplay.blit(squatIcon, (randomAppleI, randomAppleJ))
        gameDisplay.blit(shoesIcon, (randomAppleK, randomAppleL))
        gameDisplay.blit(pushupIcon, (randomAppleM, randomAppleN))
        gameDisplay.blit(tennisIcon,(randomAppleO, randomAppleP))
        gameDisplay.blit(icecreamIcon,(randomAppleQ, randomAppleR))
        gameDisplay.blit(pullUpIcon, (randomApple1, randomApple2))
        gameDisplay.blit(swimmingIcon, (randomApple3, randomApple4))
        gameDisplay.blit(alcoholIcon, (randomApple5, randomApple6))
        gameDisplay.blit(hamburgerIcon, (randomApple7, randomApple8))
        gameDisplay.blit(pingPongIcon, (randomApple9, randomApple0))
        gameDisplay.blit(boxingIcon, (randomApple01, randomApple02))
        gameDisplay.blit(weightLiftingIcon, (randomApple03, randomApple04))
        gameDisplay.blit(gameControllerIcon, (randomApple05, randomApple06))
        gameDisplay.blit(badmintonEquipmentsIcon, (randomApple07, randomApple08))
        gameDisplay.blit(cyclingIcon, (randomApple09, randomApple10))
        gameDisplay.blit(luckyCatIcon, (randomApple11, randomApple12))

        human = []
        human.append(lead_x)
        human.append(lead_y)
        human_list.append(human)

        # if len(snake_list) > snake_length:
        # del snake_list[0]

        # _*_ coding: The code below is defined for the snake turn arround and touch its body and go die. _*_
        # for eachSegment in snake_list[:-1]:
        # if eachSegment == snake_head:
        # game_over = True

        navigateHuman(block_size, human_list)

        score(scores - 1)

        pygame.display.update()
        checkShoes = False
        # This part is new error, I have to fix it right now.
        if lead_x >= randomAppleK and lead_x <= randomAppleK + Apple_thickness or lead_x + block_size >= randomAppleK and lead_x + block_size < randomAppleK + Apple_thickness:
            if lead_y >= randomAppleL and lead_y <= randomAppleL + Apple_thickness or lead_y + block_size >= randomAppleL and lead_y + block_size < randomAppleL + Apple_thickness:
                randomAppleK = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
                randomAppleL = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0
                checkShoes = True
                iconDefine(shoesIcon)

        if lead_x >= randomApple1 and lead_x <= randomApple1 + Apple_thickness or lead_x + block_size >= randomApple1 and lead_x + block_size < randomApple1 + Apple_thickness:
            if lead_y >= randomApple2 and lead_y <= randomApple2 + Apple_thickness or lead_y + block_size >= randomApple2 and lead_y + block_size < randomApple2 + Apple_thickness:
                randomApple1 = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
                randomApple2 = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0
                scores += 2
                iconDefine(pullUpIcon)

        if lead_x >= randomApple3 and lead_x <= randomApple3 + Apple_thickness or lead_x + block_size >= randomApple3 and lead_x + block_size < randomApple3 + Apple_thickness:
            if lead_y >= randomApple4 and lead_y <= randomApple4 + Apple_thickness or lead_y + block_size >= randomApple4 and lead_y + block_size < randomApple4 + Apple_thickness:
                randomApple3 = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
                randomApple4 = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0
                iconDefine(swimmingIcon)
                scores += 2

        if lead_x >= randomApple5 and lead_x <= randomApple5 + Apple_thickness or lead_x + block_size >= randomApple5 and lead_x + block_size < randomApple5 + Apple_thickness:
            if lead_y >= randomApple6 and lead_y <= randomApple6 + Apple_thickness or lead_x + block_size >= randomApple6 and lead_x + block_size < randomApple6 + Apple_thickness:
                randomApple5 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple6 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(alcoholIcon)
                scores -= 2

        if lead_x >= randomApple7 and lead_x <= randomApple7 + Apple_thickness or lead_x + block_size >= randomApple7 and lead_x + block_size < randomApple7 + Apple_thickness:
            if lead_y >= randomApple8 and lead_y <= randomApple8 + Apple_thickness or lead_x + block_size >= randomApple8 and lead_x + block_size < randomApple8 + Apple_thickness:
                randomApple7 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple8 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(hamburgerIcon)
                scores -= 2

        if lead_x >= randomApple9 and lead_x <= randomApple9 + Apple_thickness or lead_x + block_size >= randomApple9 and lead_x + block_size < randomApple9 + Apple_thickness:
            if lead_y >= randomApple0 and lead_y <= randomApple0 + Apple_thickness or lead_x + block_size >= randomApple0 and lead_x + block_size < randomApple0 + Apple_thickness:
                randomApple9 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple0 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(pingPongIcon)
                scores += 2

        if lead_x >= randomApple01 and lead_x <= randomApple01 + Apple_thickness or lead_x + block_size >= randomApple01 and lead_x + block_size < randomApple01 + Apple_thickness:
            if lead_y >= randomApple02 and lead_y <= randomApple02 + Apple_thickness or lead_x + block_size >= randomApple02 and lead_x + block_size < randomApple02 + Apple_thickness:
                randomApple01 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple02 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(boxingIcon)
                scores += 2

        if lead_x >= randomApple03 and lead_x <= randomApple03 + Apple_thickness or lead_x + block_size >= randomApple03 and lead_x + block_size < randomApple03 + Apple_thickness:
            if lead_y >= randomApple04 and lead_y <= randomApple04 + Apple_thickness or lead_x + block_size >= randomApple04 and lead_x + block_size < randomApple04 + Apple_thickness:
                randomApple03 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple04 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(weightLiftingIcon)
                scores += 2

        if lead_x >= randomApple05 and lead_x <= randomApple05 + Apple_thickness or lead_x + block_size >= randomApple05 and lead_x + block_size < randomApple05 + Apple_thickness:
            if lead_y >= randomApple06 and lead_y <= randomApple06 + Apple_thickness or lead_x + block_size >= randomApple06 and lead_x + block_size < randomApple06 + Apple_thickness:
                randomApple05 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple06 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(gameControllerIcon)
                scores -= 2

        if lead_x >= randomApple07 and lead_x <= randomApple07 + Apple_thickness or lead_x + block_size >= randomApple07 and lead_x + block_size < randomApple07 + Apple_thickness:
            if lead_y >= randomApple08 and lead_y <= randomApple08 + Apple_thickness or lead_x + block_size >= randomApple08 and lead_x + block_size < randomApple08 + Apple_thickness:
                randomApple07 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple08 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(badmintonEquipmentsIcon)
                scores += 2

        if lead_x >= randomApple09 and lead_x <= randomApple09 + Apple_thickness or lead_x + block_size >= randomApple09 and lead_x + block_size < randomApple09 + Apple_thickness:
            if lead_y >= randomApple10 and lead_y <= randomApple10 + Apple_thickness or lead_x + block_size >= randomApple10 and lead_x + block_size < randomApple10 + Apple_thickness:
                randomApple09 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple10 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(cyclingIcon)
                scores += 2

        if lead_x >= randomApple07 and lead_x <= randomApple07 + Apple_thickness or lead_x + block_size >= randomApple07 and lead_x + block_size < randomApple07 + Apple_thickness:
            if lead_y >= randomApple08 and lead_y <= randomApple08 + Apple_thickness or lead_x + block_size >= randomApple08 and lead_x + block_size < randomApple08 + Apple_thickness:
                randomApple07 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple08 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(badmintonEquipmentsIcon)
                scores += 50

        if lead_x >= randomApple07 and lead_x <= randomApple07 + Apple_thickness or lead_x + block_size >= randomApple07 and lead_x + block_size < randomApple07 + Apple_thickness:
            if lead_y >= randomApple08 and lead_y <= randomApple08 + Apple_thickness or lead_x + block_size >= randomApple08 and lead_x + block_size < randomApple08 + Apple_thickness:
                randomApple07 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple08 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(badmintonEquipmentsIcon)
                scores += 2
        
        if lead_x >= randomApple07 and lead_x <= randomApple07 + Apple_thickness or lead_x + block_size >= randomApple07 and lead_x + block_size < randomApple07 + Apple_thickness:
            if lead_y >= randomApple08 and lead_y <= randomApple08 + Apple_thickness or lead_x + block_size >= randomApple08 and lead_x + block_size < randomApple08 + Apple_thickness:
                randomApple07 = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomApple08 = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                # iconDefine(badmintonEquipmentsIcon)
                scores += 2

        if lead_x >= randomAppleA and lead_x <= randomAppleA + Apple_thickness or lead_x + block_size >= randomAppleA and lead_x + block_size < randomAppleA + Apple_thickness:
            if lead_y >= randomAppleB and lead_y <= randomAppleB + Apple_thickness or lead_y + block_size >= randomAppleB and lead_y + block_size < randomAppleB + Apple_thickness:
                randomAppleA = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
                randomAppleB = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0
                scores += 3
                iconDefine(runningManIcon)
        checkDouble = 1

        if lead_x >= randomAppleC and lead_x <= randomAppleC + Apple_thickness or lead_x + block_size >= randomAppleC and lead_x + block_size <= randomAppleC + Apple_thickness:
            if lead_y >= randomAppleD and lead_y <= randomAppleD + Apple_thickness or lead_x + block_size >= randomAppleD and lead_x + block_size <= randomAppleD + Apple_thickness:
                randomAppleC = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
                randomAppleD = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0
                # snake_length += 3
                if checkDouble == 1:
                    game_over = True
                    pygame.display.update()
                # FPS = 20
                # pygame.display.update()


        if checkDouble == 1:
            checkAnotherLife = False

        if checkDouble == 0:
            checkAnotherLife = True


        if lead_x >= randomAppleQ and lead_x <= randomAppleQ + Apple_thickness or lead_x + block_size >= randomAppleQ and lead_x + block_size < randomAppleQ + Apple_thickness:
            if lead_y >= randomAppleR and lead_y <= randomAppleR + Apple_thickness or lead_x + block_size >= randomAppleR and lead_x + block_size < randomAppleR + Apple_thickness:
                randomAppleQ = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomAppleR = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                iconDefine(icecreamIcon)
                scores -= 2
                

        if lead_x >= randomAppleE and lead_x <= randomAppleE + Apple_thickness or lead_x + block_size >= randomAppleE and lead_x + block_size < randomAppleE + Apple_thickness:
            if lead_y >= randomAppleF and lead_y <= randomAppleF + Apple_thickness or lead_x + block_size >= randomAppleF and lead_x + block_size < randomAppleF + Apple_thickness:
                randomAppleE = round(random.randrange(0, display_width - block_size))#/ 10.0) * 10.0
                randomAppleF = round(random.randrange(0, display_height - block_size))# / 10.0) * 10.0
                iconDefine(franceFriesIcon)
                scores -= 2
                # if checkAnotherLife == False:
                #     game_over = True
                #     pygame.display.update()

        if lead_x >= randomAppleX and lead_x <= randomAppleX + Apple_thickness or lead_x + block_size >= randomAppleX and lead_x + block_size <= randomAppleX + Apple_thickness:
            if lead_y >= randomAppleY and lead_y <= randomAppleY + Apple_thickness or lead_x + block_size >= randomAppleY and lead_x + block_size <= randomAppleY + Apple_thickness:
                randomAppleX = round(random.randrange(0, display_width - Apple_thickness))  # / 10.0) * 10.0
                randomAppleY = round(random.randrange(0, display_height - Apple_thickness))  # / 10.0) * 10.0
                iconDefine(sandwichIcon)
                scores -= 1

        if lead_x >= randomAppleG and lead_x <= randomAppleG + Apple_thickness or lead_x + block_size >= randomAppleG and lead_x + block_size <= randomAppleG + Apple_thickness:
            if lead_y >= randomAppleH and lead_y <= randomAppleH + Apple_thickness or lead_x + block_size >= randomAppleH and lead_x + block_size <= randomAppleH + Apple_thickness:
                randomAppleG = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomAppleH = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                scores -= 2
                iconDefine(beerIcon)

        if lead_x >= randomAppleO and lead_x <= randomAppleO + Apple_thickness or lead_x + block_size >= randomAppleO and lead_x + block_size <= randomAppleO + Apple_thickness:
            if lead_y >= randomAppleP and lead_y <= randomAppleP + Apple_thickness or lead_x + block_size >= randomAppleP and lead_x + block_size <= randomAppleP + Apple_thickness:
                randomAppleO = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomAppleP = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                iconDefine(tennisIcon)
                scores += 2

        if lead_x >= randomAppleM and lead_x <= randomAppleM + Apple_thickness or lead_x + block_size >= randomAppleM and lead_x + block_size <= randomAppleM + Apple_thickness:
            if lead_y >= randomAppleN and lead_y <= randomAppleN + Apple_thickness or lead_x + block_size >= randomAppleN and lead_x + block_size <= randomAppleN + Apple_thickness:
                randomAppleM = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomAppleN = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                iconDefine(pushupIcon)
                scores += 2

        if lead_x >= randomAppleI and lead_x <= randomAppleI + Apple_thickness or lead_x + block_size >= randomAppleI and lead_x + block_size <= randomAppleI + Apple_thickness:
            if lead_y >= randomAppleJ and lead_y <= randomAppleJ + Apple_thickness or lead_x + block_size >= randomAppleJ and lead_x + block_size <= randomAppleJ + Apple_thickness:
                randomAppleI = round(random.randrange(0, display_width - block_size))  # / 10.0) * 10.0
                randomAppleJ = round(random.randrange(0, display_height - block_size))  # / 10.0) * 10.0
                iconDefine(squatIcon)
                scores += 1

        if scores - 1 >= 50:
            level2()
            pygame.display.update()
        clock.tick(FPS)
        # _*_ coding: it's 20 frame per second _*_
    time.sleep(0.01)
    pygame.quit()
    quit()


def gameIntroduction():
    intro = True

    while intro:

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                quit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_s:
                    intro = False
                # if event.key == pygame.K_q:
                #     pygame.quit()
                #     quit()
        gameDisplay.fill(ColorNotKnown)
        messageIntoScreen0("Super Man Against Obesity", specialcolor, -260, "large")
        messageIntoScreenEm("Version 1.0 by @author Meozz", RedLightness50percent, -200, "special")
        messageIntoScreen0("=======================Instruction For Level 1=======================", black, -110,
                           "medium")
        messageIntoScreen1("                         1.U have to control super man & don't grab too much fast foods.", MidnightBlue, -20, "small")
        messageIntoScreen1("                                    2.If u eat too much fast food, u'll get trouble with ur weight and get fat.", OrrangeRed, 30, "small")
        # messageIntoScreen1("       3.If u run into three-leaves clover, u get another life to survive.", Green,20, "small")
        # messageIntoScreen1("               --> Which means u will not able to die when u run into human after 1 turn. ", Green, 50, "small")
        messageIntoScreen1("                                 3.If u do exercises enough to spend all calories from fast food, u'll ok.", Green, 80, "small")
        messageIntoScreen1("                       4.Score will increase if u try to do a lot of sports and work out.", CornFlowerBlue, 130, "small")
        # messageIntoScreen1(
            # "                              5.U run into human, he'll try to kill you, u die, GAME OVER...",
            # MediumSeaGreen, 120, "small")
        # messageIntoScreen1("              6.If u run into the edges or run into yourself, u'll die.", SlateGray, 155, "small")
        messageIntoScreen1("                      5.But otherwise, score'll decrease if u grab fast food, or bomb.", MediumSeaGreen, 180, "small")
        messageIntoScreen1("    6.Press S to start the game & P to pause the game.", CadetBlue, 230, "small")
        messageIntoScreen1("7.U have to archieve 20 points to unlock level 2.", Tomato, 280, "small")
        pygame.display.update()
        clock.tick(10)


gameIntroduction()
level1()

if __name__ == '__gameLoop__':
    gameLoop()
# _*_ coding: if __name__ == '__gameLoop__': _*_
#     _*_ coding: gameLoop() _*_