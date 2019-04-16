# How to compile

1. `idlj -fall StockPrice.idl`
2. `javac *.java StockPriceApp/*.java`

# How to execute

## Start ORB 

`orbd -ORBInitialPort 1050`

## Start Server

`java StockPriceServer -ORBInitialPort 1050 -ORBInitialHost localhost`

## Start Client 

`java StockPriceClient -ORBInitialPort 1050 -ORBInitialHost localhost`
