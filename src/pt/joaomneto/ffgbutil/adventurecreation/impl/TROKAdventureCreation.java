package pt.joaomneto.ffgbutil.adventurecreation.impl;

import java.io.BufferedWriter;
import java.io.IOException;

import pt.joaomneto.ffgbutil.R;
import pt.joaomneto.ffgbutil.adventure.Adventure.AdventureFragmentRunner;
import pt.joaomneto.ffgbutil.adventurecreation.AdventureCreation;
import pt.joaomneto.ffgbutil.adventurecreation.impl.fragments.tkok.TROKVitalStatisticsFragment;
import pt.joaomneto.ffgbutil.util.DiceRoller;
import android.view.View;

public class TROKAdventureCreation extends AdventureCreation {

	
	private int currentWeapons = -1;
	private int currentShields = -1;


	public TROKAdventureCreation() {
		super();
		fragmentConfiguration.clear();
		fragmentConfiguration.put(0, new AdventureFragmentRunner(
				R.string.title_adventure_creation_vitalstats,
				"pt.joaomneto.ffgbutil.adventurecreation.impl.fragments.ff.TKOKVitalStatisticsFragment"));
		
	}

	@Override
	protected void storeAdventureSpecificValuesInFile(BufferedWriter bw)
			throws IOException {

		bw.write("currentWeapons="+currentWeapons+"\n");
		bw.write("currentArmour="+currentShields+"\n");
		bw.write("initialWeapons="+currentWeapons+"\n");
		bw.write("initialArmour="+currentShields+"\n");
		bw.write("missiles=2\n");
		bw.write("provisions=4\n");
		bw.write("provisionsValue=6\n");
		bw.write("gold=5000\n");
	}
	
	private TROKVitalStatisticsFragment getTKOKVitalStatisticsFragment() {
		TROKVitalStatisticsFragment tkokVitalStatsFragment = (TROKVitalStatisticsFragment) getSupportFragmentManager()
				.getFragments().get(0);
		return tkokVitalStatsFragment;
	}
	

	@Override
	protected void rollGamebookSpecificStats(View view) {
		currentWeapons = DiceRoller.rollD6()+6;
		currentShields = DiceRoller.rollD6();
		getTKOKVitalStatisticsFragment().getWeaponsValue().setText(""+currentWeapons);
		getTKOKVitalStatisticsFragment().getShieldsValue().setText(""+currentShields);
		
	}

}
