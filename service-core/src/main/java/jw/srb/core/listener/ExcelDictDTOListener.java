package jw.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import jw.srb.core.mapper.DictMapper;
import jw.srb.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {


    private DictMapper dictMapper;

    //数据列表
    private List<ExcelDictDTO> list =  new ArrayList<>();

    //每3000条记录批量存储一次数据
    private static final int BATCH_COUNT = 3000;

    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO data, AnalysisContext context) {
        log.info("解析到一条记录: {}", data);
        //将数据存入数据列表
        list.add(data);
        if(list.size() >= BATCH_COUNT){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //当最后剩余的数据记录数不足BATCH_COUNT时，我们最终一次性存储剩余数据
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData(){
        log.info("{} 条数被存储到数据库......", list.size());
        //调用mapper层的批量save方法：save list对象
        dictMapper.insertBatch(list);
        log.info("{} 条数被存储到数据库成功！", list.size());
    }
}
