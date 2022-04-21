package healthyLife.serverApi.models.relation;

import java.math.BigInteger;
import java.util.Objects;

public class RelationInfoModel {

    public String user;
    public RelationStatus status;

    public RelationInfoModel(String user, BigInteger status) {
        this.user = user;
        this.status = RelationStatus.values()[status.intValue()];
    }

    public RelationInfoModel(String user, RelationStatus status) {
        this.user = user;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationInfoModel that = (RelationInfoModel) o;
        return user.equals(that.user) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, status);
    }
}
