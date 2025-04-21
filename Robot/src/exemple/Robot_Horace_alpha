#1. FT
#2. TT si oui MI si non PS
#3. TT si oui MI si non FT
#4. TT si oui TR si non AL
#5. AL
#6. FT
#7. TT si oui TC si non FT
#8. TT si oui FT si non PS
#9. AL
#10 TT si oui MI si non PS

loop
	fuite ()
	scan (4)
	if (detected == true)
	  mine ()
	else
	  poursuite()
	endif
	if (detected == true)
	  mine ()
	else
	  fuite ()
	endif
	
	scan (4)
	if (detected == true)
	   tir (1,0)
	   tir (-1,0)
	else
	    avancer (Rand[2], Rand[2])
	endif
	avancer (Rand[2], Rand[2])
	fuite ()
	
	scan (4)
	if (detected == true)
	   tir (0,1)
	   tir (0,-1)
	else
	   fuite ()
	endif
	
	scan (4)
	if (detected == true)
	   fuite ()
	else
	   poursuite ()
	endif
	avancer (Rand[2], Rand[2])
	
	scan (4)
	if (detected == true)
	  mine ()
	else
	  poursuite ()
	endif
endloop