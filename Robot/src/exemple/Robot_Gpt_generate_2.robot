loop
	scan(4)
	if (detected == true)
	    tir(1,0)
	    tir(-1,0)
	    tir(0,1)
	    tir(0,-1)
	    mine()
	    invisible()
	    fuite()
	else
	    poursuite()
	    avancer(Rand[2], Rand[2])
	    if (Math.floor(Math.random() * 1) == 0)     // Probabilité d’utiliser l’invisibilité même sans détection
	        invisible()
	    endif
	    if (Math.floor(Math.random() * 1) == 0)     // Piège aléatoire
	        mine()
	    endif
	endif
	
	avancer(Rand[2], Rand[2])
endloop
