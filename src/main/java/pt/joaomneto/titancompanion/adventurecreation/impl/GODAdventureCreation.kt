package pt.joaomneto.titancompanion.adventurecreation.impl

import java.io.BufferedWriter
import pt.joaomneto.titancompanion.adventurecreation.AdventureCreation

class GODAdventureCreation : AdventureCreation() {
    override fun storeAdventureSpecificValuesInFile(bw: BufferedWriter) {
        super.storeAdventureSpecificValuesInFile(bw)
        bw.write("provisions=0\n")
        bw.write("provisionsValue=4\n")
        bw.write("weapon=UNARMED\n")
        bw.write("poison=0\n")
        bw.write("smokeOil=10\n")
    }
}
