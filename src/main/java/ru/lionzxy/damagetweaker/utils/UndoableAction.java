package ru.lionzxy.damagetweaker.utils;

import minetweaker.IUndoableAction;

public class UndoableAction implements IUndoableAction {
	private final String mDescription;
	private final Runnable mApplyAction;
	private final String mUndoDescription;
	private final Runnable mUndoAction;

	public UndoableAction(String pDescription, Runnable pApplyAction, String pUndoDescription, Runnable pUndoAction) {
		mDescription = pDescription;
		mApplyAction = pApplyAction;
		mUndoDescription = pUndoDescription;
		mUndoAction = pUndoAction;
	}

	@Override
	public void apply() {
		mApplyAction.run();
	}

	@Override
	public boolean canUndo() {
		return mUndoAction != null;
	}

	@Override
	public String describe() {
		return mDescription;
	}

	@Override
	public String describeUndo() {
		return mUndoDescription;
	}

	@Override
	public Object getOverrideKey() {
		return null;
	}

	@Override
	public void undo() {
		mUndoAction.run();
	}

}
