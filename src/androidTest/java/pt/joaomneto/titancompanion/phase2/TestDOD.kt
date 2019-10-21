package pt.joaomneto.titancompanion.phase2

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith
import pt.joaomneto.titancompanion.consts.FightingFantasyGamebook.DAGGERS_OF_DARKNESS
import pt.joaomneto.titancompanion.phase1.TestTFOD

@LargeTest
@RunWith(AndroidJUnit4::class)
class TestDOD : TestTFOD() {

    override val gamebook = DAGGERS_OF_DARKNESS
}