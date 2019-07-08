
public class Edge {

	private String srcLabel;
	private String tarLabel;
	private Integer weight;
	
	public String getSrcLabel() {
		return srcLabel;
	}

	public void setSrcLabel(String srcLabel) {
		this.srcLabel = srcLabel;
	}

	public String getTarLabel() {
		return tarLabel;
	}

	public void setTarLabel(String tarLabel) {
		this.tarLabel = tarLabel;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}



public String toString() {
		
	return weight > 0 ? " " + srcLabel + " " + tarLabel + " " + weight : "";
}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((srcLabel == null) ? 0 : srcLabel.hashCode());
		result = prime * result + ((tarLabel == null) ? 0 : tarLabel.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (srcLabel == null) {
			if (other.srcLabel != null)
				return false;
		} else if (!srcLabel.equals(other.srcLabel))
			return false;
		if (tarLabel == null) {
			if (other.tarLabel != null)
				return false;
		} else if (!tarLabel.equals(other.tarLabel))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}
	
	
	
	
}
