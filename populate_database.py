import csv
import os
import random
import names

os.chdir('data')

seed = random.seed(int)

#menu item list
menu_item = []
with open('menu_item.csv', newline='') as menuItem:
    menuItemReader = csv.reader(menuItem)
    for row in menuItemReader:
        menu_item.append(row)

#create writers and open files
transaction = open('transaction.csv', 'w')
transactionItem = open('transaction_item.csv', 'w')
inventory = open('inventory.csv', 'w')
order = open('order.csv', 'w')

transactionWriter = csv.writer(transaction)
transactionItemWriter = csv.writer(transactionItem)
inventoryWriter = csv.writer(inventory)
orderWriter = csv.writer(order)

#for 1 day in 1 year
for i in range(0,365):
    transID = 0
    #generate transactions
    for j in range(0,275):
        trans = []
        trans.append(transID)
        trans.append(random.randrange(0,2))
        trans.append(names.get_full_name())
        numberItems = random.randrange(1,4)
        for k in range(numberItems):
            #pick random line from menu_item, add cost to transaction, decrement inventory
        #timestamp transaction
        #add to csv
    #account for gamedays



    #update inventory at end of day

    #place order if needed at end of day

#close files