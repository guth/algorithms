import Image
# (100, 99, 99, 98), (98, 97, 97, 96)...
# (100, 100-1, 100-1, 100-2), ...
wire = Image.open("assets/wire.png")
ansImg = Image.new(wire.mode, (100,100))

dirs = [(1, 0), (0, 1), (-1, 0), (0, -1)]
subs = [1, 0, 1, 0]

x, y, p = -1, 0, 0
steps = 100

while steps > 0:
	for i in xrange(4):
		dxy = dirs[i]
		sub = subs[i]
		
		for s in xrange(steps):
			x,y = x+dxy[0], y+dxy[1]
			wPix = wire.getpixel((p,0))
			ansImg.putpixel((x,y), wPix)
			p += 1

		steps -= sub

ansImg.save('wire2.png')