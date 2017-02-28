package com.easylotto.core.entity;

public class Prize {

	    private int index;
	    private String prizeId;
	    private String prizeName;
	    private double probability;

	    public Prize(int index, String prizeId, String prizeName, double probability) {
	        this.index = index;
	        this.prizeId = prizeId;
	        this.prizeName = prizeName;
	        this.probability = probability;
	    }
	    
	    public Prize( String prizeName, double probability) {
	        this.prizeName = prizeName;
	        this.probability = probability;
	    }

	    public int getIndex() {
	        return index;
	    }

	    public void setIndex(int index) {
	        this.index = index;
	    }



	    public String getPrizeId() {
			return prizeId;
		}

		public void setPrizeId(String prizeId) {
			this.prizeId = prizeId;
		}

		public String getPrizeName() {
			return prizeName;
		}

		public void setPrizeName(String prizeName) {
			this.prizeName = prizeName;
		}

		public double getProbability() {
	        return probability;
	    }

	    public void setProbability(double probability) {
	        this.probability = probability;
	    }

	    @Override
	    public String toString() {
	        return "Gift [index=" + index + ", prizeId=" + prizeId + ", prizeName=" + prizeName + ", probability=" + probability + "]";
	    }

	}

