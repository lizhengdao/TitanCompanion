package pt.joaomneto.titancompanion.phase2

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith
import pt.joaomneto.titancompanion.consts.FightingFantasyGamebook
import pt.joaomneto.titancompanion.phase1.TestST

@LargeTest
@RunWith(AndroidJUnit4::class)
class TestHOTW : TestST() {

    override val gamebook = FightingFantasyGamebook.HOWL_OF_THE_WEREWOLF
}