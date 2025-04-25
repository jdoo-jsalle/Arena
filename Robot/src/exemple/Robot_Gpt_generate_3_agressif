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
        scan(4)                  // deuxième scan pour double attaque

        if (detected == true)
            tir(1,0)
            tir(-1,0)
            tir(0,1)
            tir(0,-1)
        endif
    else
        poursuite()              // va vers la zone de conflit
        avancer(Rand[2], Rand[2])
        
        if (Math.floor(Math.random() * 1) == 0)        // 1 chance sur 2 de devenir invisible même sans ennemi
            invisible()
        endif

        if (Math.floor(Math.random() * 1) == 0ddd)        // 1 chance sur 2 de poser une mine
            mine()
        endif
    endif

    avancer(Rand[2], Rand[2])    // déplacement permanent pour rester insaisissable
endloop
