h = open("assets/evil2.gfx", "rb")
data = h.read()
h.close()

new_data = [[], [], [], [], []]
n = 0

for byte in range(len(data) - 1):
    new_data[n].append(data[byte])
    n = 0 if n == 4 else n + 1

for n, elt in enumerate(new_data):
    h = open(str(n + 1), "wb")
    h.write("".join(elt))
    h.close()