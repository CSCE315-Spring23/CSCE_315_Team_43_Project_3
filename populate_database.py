import csv
import os
import random
import names
from datetime import datetime
from datetime import timedelta

os.chdir('data')

seed = random.seed(int)

#menu item list (format is [PK,Name,Price,Ingredient_Amount,Type])
menu_item = []
with open('menu_item.csv', newline='') as menuItem:
    menuItemReader = csv.reader(menuItem, delimiter=',')
    for row in menuItemReader:
        menu_item.append(row)

#print(menu_item)
#create writers and open files
transaction = open('transaction.csv', 'w')
transactionItem = open('transaction_item.csv', 'w')
inventory = open('inventory.csv', 'w')
order = open('order.csv', 'w')

transactionWriter = csv.writer(transaction)
transactionItemWriter = csv.writer(transactionItem)
inventoryWriter = csv.writer(inventory)
orderWriter = csv.writer(order)

timeInc = timedelta(days=1)

#for 1 day in 1 year
dt = datetime(2022,1,1,1,1,1,0) #first day/time
for i in range(0,365):
    transID = 0
    #generate transactions (format is [ID, employee_ID, name, price, time])
    maxDailyTransactions = 275
    for j in range(0,maxDailyTransactions):
        trans = []
        trans.append(transID)
        trans.append(random.randrange(0,2))
        #trans.append(names.get_full_name())
        trans.append("karen")
        numberItems = random.randrange(1,4)
        costTransaction = 0
        #pick random line from menu_item, add cost to transaction, decrement inventory
        for k in range(numberItems):
            item = menu_item[random.randrange(1,len(menu_item))]
            costTransaction += float(item[2]) * float(item[3])
            #TODO: subtract from inventory
        trans.append(costTransaction)
        #timestamp transaction
        trans.append(dt)
        dt += timeInc/maxDailyTransactions
        #add to csv 
        transactionWriter.writerow(trans)
    #account for gamedays



    #update inventory at end of day

    #place order if needed at end of day

#close files
transaction.close()
transactionItem.close()
inventory.close()
order.close()