import pickle
s = open('assets/5.in').read()
p = pickle.loads(s)

for row in p:
	rows = ""
	for item in row:
		rows += item[0]*item[1]
	print rows