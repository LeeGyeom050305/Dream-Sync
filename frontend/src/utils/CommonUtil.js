export const noData = (data) => {
    return [undefined, null, "", "null", [], {}, NaN].includes(data);
};

const convert = (columnInfo, item, excludeColumn) => {
    return columnInfo.reduce((res, column) => {
        if (excludeColumn === undefined || !excludeColumn.includes(column.field)) {
            // menuCodeDetailName이 없으면 field를 사용
            const columnKey = column.menuCodeDetailName || column.field;

            // 해당 key가 item에 있는지 확인
            if (item.hasOwnProperty(columnKey)) {
                res[columnKey] = item[columnKey];
            } else {
                console.log(`Property ${columnKey} not found in item.`);
            }
        }
        return res;
    }, {});
};

export const convertToGridData = ({
                                      columnInfo,
                                      ItemList,
                                      stIndex,
                                      excludeColumn,
                                  }) => {
    if (columnInfo !== undefined && ItemList !== undefined) {
        return ItemList.map((item, index) => {
            const newItem = convert(columnInfo, item, excludeColumn);
            if (stIndex && stIndex !== undefined) {
                newItem.id = stIndex + index + 1;
            }
            return newItem;
        });
    }
};

export const matchColumnsAccordingToUser = (
    totalColumnInfo,
    userColumn,
    columnOption
) => {
    const column = (totalColumnInfo || [])
        .filter((item) => userColumn.includes(item.menuCodeDetailName))
        .map((item) => {
            const option = columnOption?.find(
                (option) => option.column === item.menuCodeDetailName
            );
            return option
                ? {
                    headerName: item.menuCodeDetailDisplayName,
                    field: item.menuCodeDetailName,
                    ...(({column, ...rest}) => rest)(option),
                }
                : {
                    headerName: item.menuCodeDetailDisplayName,
                    field: item.menuCodeDetailName,
                };
        });
    return column;
};

export function getFieldValue(obj, path) {
    if (!obj || !path) return undefined;
    return path.split('.').reduce((o, key) => (o ? o[key] : undefined), obj);
}
