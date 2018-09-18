import sys

def main():
    
    list = fileRead()
    
    numberOfElements = list[0]

    del list[0]
    
    shieldValue = [0] * len(list)
    attackPlan  = [0] * len(list)

    for i in range(0, len(list)):
       temp = list[i].split()
       shieldValue[i] = int(temp[0])
       attackPlan[i] = temp[1]

    for i in range(0, len(shieldValue)):
        print("CASE " + str(i) + ": " + calculateHack(shieldValue[i], attackPlan[i]))


#Iterates through the current list
#Calculates the damage value of the current list
def checkDamage(shieldValue, attackPlan):
    
    damageValue = 1

    damageDone = 0

    for i in range(0, len(attackPlan)):
            if(attackPlan[i] == "S"):
                damageDone += damageValue

            else:
                damageValue *= 2

    if(damageDone <= shieldValue):
            return True

    else:
        return False


            
def calculateHack(shieldValue, attackString):
    
    attackPlan = list(attackString)

    temp1 = str()
    temp2 = str()

    x = 0
    
    numHacks = 0

    swapped = False

    while(swapped == False):
    
        if(checkDamage(shieldValue, attackPlan)):
            swapped = True

        else:
            x += 1

            swapped = True

            for i in range(0, len(attackPlan) - x):
                temp1 = attackPlan[i]
                temp2 = attackPlan[i + 1]

                if((temp1 == "C") & (temp2 == "S")):
                    attackPlan[i] = temp2
                    attackPlan[i + 1] = temp1

                    swapped = False

                    numHacks += 1

    if(checkDamage(shieldValue, attackPlan)):
        return str(numHacks)

    else:
        return "IMPOSSIBLE"
                            




    return numHacks

def fileRead():

    if(len(sys.argv) > 1):
        file = open(sys.argv[1], 'r')

    else:
        file = open('input.txt', 'r')

    info = file.readlines()

    for i in range(0, len(info) - 1):
        info[i] = str.strip(info[i])

    file.close

    return info

if __name__ == '__main__':
    main()