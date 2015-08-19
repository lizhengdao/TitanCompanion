package pt.joaomneto.ffgbutil.adventure.impl.fragments.rc;

import java.util.ArrayList;
import java.util.List;

import pt.joaomneto.ffgbutil.R;
import pt.joaomneto.ffgbutil.adventure.Adventure;
import pt.joaomneto.ffgbutil.adventure.AdventureFragment;
import pt.joaomneto.ffgbutil.adventure.impl.RCAdventure;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class RCAdventureRobotFragment extends AdventureFragment {

	protected static Integer[] gridRows;

	protected List<Robot> robots = new ArrayList<>();

	protected ListView robotListView = null;
	protected Button addRobotButton = null;
	protected View rootView = null;

	public RCAdventureRobotFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rootView = inflater.inflate(R.layout.fragment_22rc_adventure_robots,
				container, false);

		init();

		return rootView;
	}

	protected void init() {

		RCAdventure adv = (RCAdventure) this.getActivity();

		addRobotButton = (Button) rootView.findViewById(R.id.addRobotButton);
		robotListView = (ListView) rootView.findViewById(R.id.robotList);
		robotListView.setAdapter(new RobotListAdapter(adv, robots));

		addRobotButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				addRobotButtonOnClick();
			}

		});

		refreshScreensFromResume();
	}

	protected void addRobotButtonOnClick() {
		addRobotButtonOnClick(R.layout.component_22rc_add_robot);
	}

	protected void addRobotButtonOnClick(int layoutId) {

		final RCAdventure adv = (RCAdventure) getActivity();

		final View addRobotView = adv.getLayoutInflater().inflate(
				R.layout.component_22rc_add_robot, null);

		final InputMethodManager mgr = (InputMethodManager) adv
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		CheckBox alternateForm = (CheckBox) addRobotView
				.findViewById(R.id.alternateFormValue);

		final RelativeLayout alternateStatsGroup = (RelativeLayout) addRobotView
				.findViewById(R.id.alternateStatsGroup);

		alternateForm
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						alternateStatsGroup
								.setVisibility(isChecked ? View.VISIBLE
										: View.GONE);
					}
				});

		AlertDialog.Builder builder = new AlertDialog.Builder(adv);

		builder.setTitle("Add Robot")
				.setCancelable(false)
				.setNegativeButton("Close",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								mgr.hideSoftInputFromWindow(
										addRobotView.getWindowToken(), 0);
								dialog.cancel();
							}
						});

		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				mgr.hideSoftInputFromWindow(addRobotView.getWindowToken(), 0);

				EditText nameAltValue = (EditText) addRobotView
						.findViewById(R.id.nameAltValue);
				EditText armorAltValue = (EditText) addRobotView
						.findViewById(R.id.armorAltValue);
				EditText combatBonusAltValue = (EditText) addRobotView
						.findViewById(R.id.bonusAltValue);
				Spinner speedAltValue = (Spinner) addRobotView
						.findViewById(R.id.speedAltValue);

				EditText nameValue = (EditText) addRobotView
						.findViewById(R.id.nameValue);
				EditText armorValue = (EditText) addRobotView
						.findViewById(R.id.armorValue);
				EditText combatBonusValue = (EditText) addRobotView
						.findViewById(R.id.bonusValue);
				Spinner speedValue = (Spinner) addRobotView
						.findViewById(R.id.speedValue);
				EditText specialAbilityValue = (EditText) addRobotView
						.findViewById(R.id.specialAbilityValue);
				CheckBox alternateForm = (CheckBox) addRobotView
						.findViewById(R.id.alternateFormValue);

				String armor = armorValue.getText().toString();
				String bonus = combatBonusValue.getText().toString();
				String specialAbility = specialAbilityValue.getText()
						.toString();
				String armorAlt = armorAltValue.getText().toString();
				String bonusAlt = combatBonusAltValue.getText().toString();
				String name = nameValue.getText().toString();
				String nameAlt = nameAltValue.getText().toString();

				boolean valid = name.length() > 0
						&& armor.length() > 0
						&& bonus.length() > 0
						&& (!alternateForm.isChecked() || (alternateForm
								.isChecked() && armorAlt.length() > 0 && bonusAlt
								.length() > 0));
				if (valid) {
					if (alternateForm.isChecked())
						addRobot(name, Integer.parseInt(armor),
								Integer.parseInt(bonus),
								(String) speedValue.getSelectedItem(),
								specialAbility.length()>0?Integer.parseInt(specialAbility):null, nameAlt,
								Integer.parseInt(armorAlt),
								Integer.parseInt(bonusAlt),
								(String) speedAltValue.getSelectedItem());
					else
						addRobot(name, Integer.parseInt(armor),
								Integer.parseInt(bonus),
								(String) speedValue.getSelectedItem(),
								specialAbility.length()>0?Integer.parseInt(specialAbility):null);
				} else {
					adv.showAlert("At least the name, armor and bonus values must be filled.");
				}

			}

		});

		AlertDialog alert = builder.create();

		Spinner speed = (Spinner) addRobotView.findViewById(R.id.speedValue);

		alert.setView(addRobotView);

		mgr.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
		speed.requestFocus();

		alert.show();
	}

	protected void addRobot(String name, Integer armor, Integer bonus,
			String speed, Integer specialAbility) {

		addRobot(name, armor, bonus, speed, specialAbility, null, null, null,
				null);
	}

	protected void addRobot(String name, Integer armor, Integer bonus,
			String speed, Integer specialAbility, String nameAlt,
			Integer armorAlt, Integer bonusAlt, String speedAlt) {

		RCAdventure adv = (RCAdventure) this.getActivity();

		for (Robot r : robots) {
			r.setActive(false);

		}

		RobotSpecialAbility abilityByReference = RobotSpecialAbility
				.getAbiliyByReference(specialAbility);

		Robot robotPosition = new Robot(
				name,
				armor,
				speed,
				bonus,
				(!RobotSpecialAbility.TROOPER_XI_HUMAN_SHIELD
						.equals(abilityByReference) || (RobotSpecialAbility.TROOPER_XI_HUMAN_SHIELD
						.equals(abilityByReference) && armor == 12)) ? abilityByReference
						: null);

		robots.add(robotPosition);

		if (armorAlt != null) {
			robotPosition = new Robot(
					nameAlt,
					armorAlt,
					speedAlt,
					bonusAlt,
					(RobotSpecialAbility.TROOPER_XI_HUMAN_SHIELD
							.equals(abilityByReference) && armorAlt == 12) ? abilityByReference
							: null);
			robots.add(robotPosition);
		}

		robots.get(robots.size() - 1).setActive(true);
		RobotListAdapter adapter = (RobotListAdapter) robotListView
				.getAdapter();
		adapter.notifyDataSetChanged();

		adv.setCurrentRobot(adapter.getCurrentRobot());
	}

	@Override
	public void refreshScreensFromResume() {
		RCAdventure adv = (RCAdventure) this.getActivity();
		RobotListAdapter adapter = (RobotListAdapter) robotListView
				.getAdapter();
		adapter.notifyDataSetChanged();

		adv.setCurrentRobot(adapter.getCurrentRobot());
	}

	public class Robot {

		String name;
		Integer armor;
		String speed;
		Integer bonus;
		boolean active;

		String location = "";
		RobotSpecialAbility robotSpecialAbility;

		public Robot(String name, Integer armor, String speed, Integer bonus,
				RobotSpecialAbility robotSpecialAbility) {
			this.name = name;
			this.armor = armor;
			this.speed = speed;
			this.bonus = bonus;
			this.robotSpecialAbility = robotSpecialAbility;
		}

		public CharSequence toGridString() {
			return ("Armor:" + armor + " Speed:" + speed + "\nCombat Bonus: "
					+ bonus + "\nLocation: " + location);
		}

		public Integer getArmor() {
			return armor;
		}

		public void setArmor(Integer armor) {
			this.armor = armor;
		}

		public String getSpeed() {
			return speed;
		}

		public void setSpeed(String speed) {
			this.speed = speed;
		}

		public Integer getBonus() {
			return bonus;
		}

		public void setBonus(Integer bonus) {
			this.bonus = bonus;
		}

		public RobotSpecialAbility getRobotSpecialAbility() {
			return robotSpecialAbility;
		}

		public void setRobotSpecialAbility(
				RobotSpecialAbility robotSpecialAbility) {
			this.robotSpecialAbility = robotSpecialAbility;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

	}

}