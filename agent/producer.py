import time
from stompest.config import StompConfig
from stompest.sync import Stomp

server = "hostname"
port = "61613"
vhost = "yourvhost"
login = "username"
passcode = "password"
destination = "/queue/test" #There're more options other than /queue/...

client = Stomp(StompConfig("http://" + "localhost" + ":" + "8080/pubsub"))
client.connect(versions = ["1.2"], host = vhost, heartBeats = (0, 60000))   #CONNECT
msgNum = int(input("Quantity of test messages: "))
for i in range(msgNum):
   message = "test msg " + str(i + 1)
   client.send(destination, body = message, headers = {"content-type": "text/plain"}, receipt = None)  #SEND
   time.sleep(1)   
   client.disconnect() #DISCONNECT
