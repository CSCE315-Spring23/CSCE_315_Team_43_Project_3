import csv
import os
import random
# import names
import string
from datetime import datetime
from datetime import timedelta

os.chdir('..\data')

#generate seed for rng
# seed = random.seed(int)

#generate set of random names
def randName(length):
    # choose from all lowercase letter
    letters = string.ascii_lowercase
    return ''.join(random.choice(letters) for i in range(length))

#menu item list (format is [PK,Name,Price,Ingredient_Amount,Type])
menu_item = []
with open('menu_item.csv', newline='') as menuItem:
    menuItemReader = csv.reader(menuItem, delimiter=',')
    for row in menuItemReader:
        menu_item.append(row)

#ingredient list (format is [Inventory_ID, Menu_ID, quantity])
ingredient_list = []
with open('ingredient_list.csv', newline='') as ingredientList:
    ingredientListReader = csv.reader(ingredientList, delimiter=',')
    for row in ingredientListReader:
        ingredient_list.append(row)

#print(menu_item)
#create writers and open files
transaction = open('transaction.csv', 'w', newline='')
transactionItem = open('transaction_item.csv', 'w', newline='')
orderList = open('order_item.csv', 'w', newline='')
orderLedger = open('order_list.csv', 'w', newline='')
inventoryUsage = open('inventory_usage.csv', 'w', newline='')
menuTracker = open('menu_tracker.csv', 'w', newline='')

transactionWriter = csv.writer(transaction)
transactionItemWriter = csv.writer(transactionItem)
orderWriter = csv.writer(orderList)
orderLedgerWriter = csv.writer(orderLedger)
inventoryUsageWriter = csv.writer(inventoryUsage)
menuTrackerWriter = csv.writer(menuTracker)

timeInc = timedelta(days=1)



#for 1 day in 1 year
dt = datetime(2022,1,1,1,1,1,0) #first day/time

totalRevenue = 0
transItemID = 0
transID = 0
orderID = 0
usageID = 0
orderLedgerID = 0
trackerID = 0

for i in range(0,365):
    usageList = []  
    for j in range(1,74):
        usageList.append([usageID, j,0,dt])
        usageID += 1

    if(i == 10 or i == 287):
        maxDailyTransactions = 180
    else:
        maxDailyTransactions = 90

    #inventory list (format is [InventoryID,Name,Type,Price,Quantity,Measurement_Type])
    inventory = []
    inventoryEndRows = []
    with open('inventory.csv', newline='') as inventoryList:
        inventoryReader = csv.reader(inventoryList, delimiter=',')
        for row in inventoryReader:
            if inventoryReader.line_num < 73:
                inventory.append(row)
            else:
                inventoryEndRows.append(row)
    inventoryItem = open('inventory.csv', 'w+', newline='')
    inventoryWriter = csv.writer(inventoryItem)

    #generate transactions (format is [ID, employee_ID, name, price, time])
    for j in range(0,maxDailyTransactions):
        trans = []
        trans.append(transID)
        trans.append(random.randrange(0,2))
        trans.append(randName(random.randrange(1,10)))
        numberItems = random.randrange(1,4)
        costTransaction = 0
        #pick random line from menu_item, add cost to transaction, decrement inventory
        alreadySelectedItems = []
        for k in range(numberItems):
            item = menu_item[random.randrange(1,61)]
            while item[0] in alreadySelectedItems:
                item = menu_item[random.randrange(1,61)]
            alreadySelectedItems.append(item[0])
            costTransaction += float(item[3]) * int(item[4])
            #write associated menu item to the ledger for the transaction
            tracker = []
            tracker.append(trackerID)
            tracker.append(transID)
            tracker.append(item[0])
            tracker.append(dt)
            menuTrackerWriter.writerow(tracker)
            trackerID += 1
            #UPDATE INVENTORY
            #find all ingredients associated with id and quantity
            ingredients_bridge = []
            for x in ingredient_list:
                if float(x[1]) == float(item[0]):
                    ingredients_bridge.append(x)
                    transactionItemWriter.writerow([transItemID, x[0], transID, x[2]])
                    transItemID += 1
                if float(x[0]) == 68:
                    ingredients_bridge.append(x)
                    transactionItemWriter.writerow([transItemID, x[0], transID, x[2]])
                    transItemID += 1
                if float(x[0]) == 71:
                    ingredients_bridge.append(x)
                    transactionItemWriter.writerow([transItemID, x[0], transID, x[2]])
                    transItemID += 1
            #subtract quantity from associated ingredient in inventory
            for x in inventory:
                for y in ingredients_bridge:
                    #for every match between inventory and ingredients
                    if int(x[0]) == int(y[0]):
                        #subtract the amount used from the inventory if not dummy
                        if int(x[0]) == 74:  
                            tempIngredient = inventory[0]
                        else:
                            tempIngredient = x
                        tempIngredient[3] = int(tempIngredient[3]) - int(y[2])
                        usageList[int(tempIngredient[0])-1][2] += int(y[2])
                        x = tempIngredient
            
        trans.append(costTransaction)
        totalRevenue += costTransaction
        #timestamp transaction
        trans.append(dt)
        dt += timeInc/maxDailyTransactions
        #add to csv 
        transactionWriter.writerow(trans)
        transID += 1
    #order list (format is [OrderID, Date_Placed, Price_of_Order])
    #order ledger (format is [inventoryID, orderID, quantity])
    order_ledger = []
    orderNeeded = False
    orderSum = 0
    for x in inventory:
        #arbitrary threshold to establish reorders
        if int(x[3]) <= 100:
            orderNeeded = True
            order_ledger.append([orderLedgerID, int(x[0]), orderID, 500])
            orderLedgerID += 1
            orderSum += 500 * float(x[2])
            #refill stock since order has been placed
            x[3] = 500

    if orderNeeded:
        orderWriter.writerow([orderID, dt, orderSum])
        orderLedgerWriter.writerows(order_ledger)
        orderID += 1
    inventoryWriter.writerows(inventory)
    inventoryWriter.writerows(inventoryEndRows)
    inventoryUsageWriter.writerows(usageList)
    inventoryItem.close()
    #account for gamedays



    #place order if needed at end of day

#prove total revenue exceeds 1,000,000
print("total revenue: ", totalRevenue)

#close files
transaction.close()
transactionItem.close()
orderList.close()
orderLedger.close()
inventoryUsage.close()