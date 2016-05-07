package az.gulf.learnquran.domain;

/**
 * Created by www.ucuz.az on 14.02.2016.
 */
public class TestObject {

    int id;
    public String correctValue;
    public int soundId;


    public TestObject(int id, String correctValue, int soundId) {
        this.id = id;
        this.correctValue = correctValue;
        this.soundId = soundId;
    }

    public String getCorrectValue() {
        return correctValue;
    }

    public void setCorrectValue(String correctValue) {
        this.correctValue = correctValue;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "id=" + id +
                ", correctValue='" + correctValue + '\'' +
                ", soundId=" + soundId +
                '}';
    }

    public boolean equals(Object obj)
    		{
        			if(this == obj)
            				return true;
        			if((obj == null) || (obj.getClass() != this.getClass()))
            				return false;
        			// object must be TestObject at this point
                TestObject test = (TestObject)obj;
        			return id == test.id &&
                			(correctValue == test.correctValue || (correctValue != null && correctValue.equals(test.correctValue)));
        		}

            		public int hashCode()
    		{
        			int hash = 7;
        			hash = 31 * hash + id;
        			hash = 31 * hash + (null == correctValue ? 0 : correctValue.hashCode());
        		return hash;
        		}


}
