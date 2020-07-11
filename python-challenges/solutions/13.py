import xmlrpclib

conn = xmlrpclib.ServerProxy("http://www.pythonchallenge.com/pc/phonebook.php")

print conn.phone('Bert')