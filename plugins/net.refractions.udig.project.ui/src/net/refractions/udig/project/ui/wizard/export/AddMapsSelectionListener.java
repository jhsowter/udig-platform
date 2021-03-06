package net.refractions.udig.project.ui.wizard.export;


import net.refractions.udig.project.ui.AdapterFactoryLabelProviderDecorator;
import net.refractions.udig.project.ui.ApplicationGIS;
import net.refractions.udig.project.ui.internal.ProjectExplorer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Opens a dialog for adding maps to the set of maps to export.
 * 
 * @author Jesse
 */
final class AddMapsSelectionListener implements SelectionListener {

	private MapSelectorPage page;

	public AddMapsSelectionListener(MapSelectorPage page) {
		this.page = page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		SelectMapsDialog dialog = new SelectMapsDialog(e.display
				.getActiveShell());
		dialog.open();
	}

	private final class SelectMapsDialog extends Dialog {

		private TreeViewer viewer;

		protected SelectMapsDialog(Shell parentShell) {
			super(parentShell);
			setShellStyle(SWT.RESIZE | SWT.SHELL_TRIM);
		}

		@Override
		protected Point getInitialSize() {
			return new Point(640, 480);
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			getShell().setText("Map Selector");

			Composite comp = (Composite) super.createDialogArea(parent);

			Label label = new Label(comp, SWT.NONE);
			label.setText("Select Maps to Export:");

			viewer = new TreeViewer(comp, SWT.MULTI | SWT.BORDER);
			viewer.setContentProvider(new ContentProvider());
			viewer.setLabelProvider(new AdapterFactoryLabelProviderDecorator(
					ProjectExplorer.getProjectExplorer().getAdapterFactory(),
					viewer));
			viewer.setAutoExpandLevel(3);
			viewer.setInput(ApplicationGIS.getProjects());

			viewer.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));

			return comp;
		}

		@Override
		protected void okPressed() {
			page.addToSelection((IStructuredSelection) viewer.getSelection());
			page.updateMapList();
			super.okPressed();
		}

	}

}