package healthyLife.serverApi.models.permission;


public class RecordModel {

    /**Адрес PermissionsContract, используется как id записи */
    public String permissionId;

    /**Адрес пользователя, с которым связана запись.
     * Если RecordModel получена из контракта пациента, то associatedUser - арес врача или поставщика медицинских услуг,
     * который создал запись, иначе associatedUser - арес пациента, которому принадлежит запись
    */
    public String associatedUser;

    /**
     *@see PermissionInfo
     */
    public PermissionInfo permissionInfo;

    public RecordModel(String permissionId, String associatedUser, PermissionInfo permissionInfo) {
        this.permissionId = permissionId;
        this.associatedUser = associatedUser;
        this.permissionInfo = permissionInfo;
    }
}
