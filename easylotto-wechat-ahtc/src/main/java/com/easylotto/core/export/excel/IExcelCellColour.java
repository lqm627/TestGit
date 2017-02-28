package com.easylotto.core.export.excel;

import jxl.format.Colour;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IExcelCellColour {

	private static final Logger logger = LoggerFactory
			.getLogger(IExcelCellColour.class);

	public static Colour value(String value) {
		if (NumberUtils.isNumber(value)) {
			Integer i = Integer.parseInt(value);
			switch (i) {
			case 1:
				return Colour.UNKNOWN;
			case 2:
				return Colour.BLACK;
			case 3:
				return Colour.WHITE;
			case 4:
				return Colour.DEFAULT_BACKGROUND1;
			case 5:
				return Colour.DEFAULT_BACKGROUND;
			case 6:
				return Colour.PALETTE_BLACK;
			case 7:
				return Colour.RED;
			case 8:
				return Colour.BRIGHT_GREEN;
			case 9:
				return Colour.BLUE;
			case 10:
				return Colour.YELLOW;
			case 11:
				return Colour.PINK;
			case 12:
				return Colour.TURQUOISE;
			case 13:
				return Colour.DARK_RED;
			case 14:
				return Colour.GREEN;
			case 15:
				return Colour.DARK_BLUE;
			case 16:
				return Colour.DARK_YELLOW;
			case 17:
				return Colour.VIOLET;
			case 18:
				return Colour.TEAL;
			case 19:
				return Colour.GREY_25_PERCENT;
			case 20:
				return Colour.GREY_50_PERCENT;
			case 21:
				return Colour.PERIWINKLE;
			case 22:
				return Colour.PLUM2;
			case 23:
				return Colour.IVORY;
			case 24:
				return Colour.LIGHT_TURQUOISE2;
			case 25:
				return Colour.DARK_PURPLE;
			case 26:
				return Colour.CORAL;
			case 27:
				return Colour.OCEAN_BLUE;
			case 28:
				return Colour.ICE_BLUE;
			case 29:
				return Colour.DARK_BLUE2;
			case 30:
				return Colour.PINK2;
			case 31:
				return Colour.YELLOW2;
			case 32:
				return Colour.TURQOISE2;
			case 33:
				return Colour.VIOLET2;
			case 34:
				return Colour.DARK_RED2;
			case 35:
				return Colour.TEAL2;
			case 36:
				return Colour.BLUE2;
			case 37:
				return Colour.SKY_BLUE;
			case 38:
				return Colour.LIGHT_TURQUOISE;
			case 39:
				return Colour.LIGHT_GREEN;
			case 40:
				return Colour.VERY_LIGHT_YELLOW;
			case 41:
				return Colour.PALE_BLUE;
			case 42:
				return Colour.ROSE;
			case 43:
				return Colour.LAVENDER;
			case 44:
				return Colour.TAN;
			case 45:
				return Colour.LIGHT_BLUE;
			case 46:
				return Colour.AQUA;
			case 47:
				return Colour.LIME;
			case 48:
				return Colour.GOLD;
			case 49:
				return Colour.LIGHT_ORANGE;
			case 50:
				return Colour.ORANGE;
			case 51:
				return Colour.BLUE_GREY;
			case 52:
				return Colour.GREY_40_PERCENT;
			case 53:
				return Colour.DARK_TEAL;
			case 54:
				return Colour.SEA_GREEN;
			case 55:
				return Colour.DARK_GREEN;
			case 56:
				return Colour.OLIVE_GREEN;
			case 57:
				return Colour.BROWN;
			case 58:
				return Colour.PLUM;
			case 59:
				return Colour.INDIGO;
			case 60:
				return Colour.GREY_80_PERCENT;
			case 61:
				return Colour.AUTOMATIC;
			}
		}
		return Colour.WHITE;
	}

}
