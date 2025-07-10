
init

	affect(x, 3)
	affect(y, 3)
endinit
loop
	avancer(Rand[2], Rand[2])
    avancer ($x,0)
    affect (x,JS:x*-1)
    avancer (0,$y)
    affect (y,JS:y*-1)
	
endloop