/*
 * Copyright 2009-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jdal.samples.vaadin;

import info.joseluismartin.beans.AppCtx;
import info.joseluismartin.dao.Filter;
import info.joseluismartin.vaadin.ui.Box;
import info.joseluismartin.vaadin.ui.table.PageableTable;

import java.awt.print.Book;

import org.jdal.samples.dao.filter.BookFilter;
import org.springframework.context.ApplicationContext;

import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
public class TestApp extends Application {
	
	ApplicationContext context = AppCtx.getInstance();
	@Override
	public void init() {
		Window mainWindow = new Window("JDAL Vaadin Sample");
		Label title = new Label("JDAL Vaadin Sample Application");
		title.setStyleName(Reindeer.LABEL_H1);
		
		PageableTable<Book> pageableTable = context.getBean("bookPageableTable", PageableTable.class);
//		Component dataSourceTable = (Component) context.getBean("dataSourceTable");
		BookFilter filter = new BookFilter();
		filter.setAuthorName("Eric");
		pageableTable.getPaginator().getModel().setFilter(filter);
		pageableTable.getPaginator().firstPage();

		Panel panel = new Panel("Table with external paginator and server side paging and sorting");
		panel.addComponent(pageableTable);
		Panel otherPanel = new Panel("Table with paginator in datasource and server side paging and sorting ");
		
	//	otherPanel.addComponent(dataSourceTable);
		VerticalLayout layout = new VerticalLayout();
                Box.addVerticalStruct(layout, 10);
                layout.addComponent(title);
                Box.addVerticalStruct(layout, 10);
		layout.addComponent(panel);
		Box.addVerticalStruct(layout, 10);
		layout.addComponent(otherPanel);
		mainWindow.setContent(layout);
		setMainWindow(mainWindow);
	}
}