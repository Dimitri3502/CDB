package com.excilys.training.controller.web;

import static com.excilys.training.controller.web.CONSTANTES.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.training.persistance.ENUMS.OrderByChamp;
import com.excilys.training.persistance.ENUMS.OrderByDirection;

@Component
public class Pagination {
	
	private static final String TOTAL_NUMBER = "totalNumber";
	private static final String PAGE_IDS = "pageIds";
	private static final String CURRENT_PAGE_NUMBER = "currentPageNumber";
	private static final int MAX_PAGE = 10; // size of page choice below the table
	private int numberPerPage; // default value
	private int currentPageNumber;
	private int offset;
	private List<Integer> pageIds;
	private int nbPage;

	
	public Page doPagination(String search, String orderBy, String orderDirection, int numberPerPage, Integer pageId, long totalNumber, ModelAndView mv) {
		
		
		nbPage = (int) Math.ceil(((double) totalNumber) / numberPerPage);

		if (!(pageId == null)) {

			if (pageId > nbPage) {
				currentPageNumber = nbPage;
			}

			else if (pageId < 0) {
				currentPageNumber = 0;
			} else {
				currentPageNumber = pageId;

			}
		}

		setOffset(currentPageNumber * numberPerPage);
		pageIds = new ArrayList<>();
		int len = 0;
		for (int number = Math.max(0, currentPageNumber - MAX_PAGE / 2); (len < MAX_PAGE)
				&& (number <= nbPage); ++number) {
			pageIds.add(number);
			len = pageIds.size();
		}
		mv.addObject(TOTAL_NUMBER, totalNumber);
		mv.addObject(PAGE_IDS, pageIds);
		mv.addObject(CURRENT_PAGE_NUMBER, currentPageNumber);
		mv.addObject(CHAMP_NB_PAGE, nbPage);
		mv.addObject(ATT_NUMBER_PER_PAGE, numberPerPage);
		mv.addObject(ATT_ORDER_BY, orderBy);
		mv.addObject(ATT_SEARCH, search);
		mv.addObject(ATT_ORDER_DIRECTION, orderDirection);
		
		return new Page(numberPerPage, offset, search, mapOrderByChamp(orderBy),
				mapOrderByDirection(orderDirection));
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getNumberPerPage() {
		return numberPerPage;
	}

	public void setNumberPerPage(int numberPerPage) {
		this.numberPerPage = numberPerPage;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}
	

	private OrderByChamp mapOrderByChamp(String s) {
		if (s == null) {
			return OrderByChamp.ID;
		} else {
			switch (s) {
			default:
			case CHAMP_ID:
				return OrderByChamp.ID;
			case CHAMP_COMPUTERNAME:
				return OrderByChamp.NAME;
			case CHAMP_INTRODUCED:
				return OrderByChamp.INTRODUCED;
			case CHAMP_DISCONTINUED:
				return OrderByChamp.DISCONTINUED;
			case CHAMP_COMPANY_NAME:
				return OrderByChamp.COMPANY;
			}
		}
	}

	private OrderByDirection mapOrderByDirection(String s) {
		if (s == null) {
			return OrderByDirection.ASC;
		} else {
			switch (s) {
			default:
			case "asc":
				return OrderByDirection.ASC;
			case "desc":
				return OrderByDirection.DESC;
			}
		}
	}
}
