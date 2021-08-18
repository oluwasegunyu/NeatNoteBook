<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-form
          layout="inline"
          :model="param"
      >
        <a-form-item>
          <a-input v-model:value="param.name">
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-space size="small">
          <a-button
              type="primary"
              @click="handleQuery()"
              html-type="submit"
          >
            查询
          </a-button>
          <a-button type="primary" @click="add()" >
            新增
          </a-button>
          </a-space>
        </a-form-item>
      </a-form>
      <a-table
          :columns="columns"
          :data-source="categorys"
          :row-key="record => record.id"
          :loading="loading"
          :pagination="false"

      >
        <template v-slot:action="{record}">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-popconfirm
                title="确认删除？"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record.id)"
            >
              <a-button type="danger">
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal v-model:visible="modalVisible" title="分类表单" @ok="handleModalOk">
    <a-form :model="category" >
      <a-form-item label="名称">
        <a-input v-model:value="category.name" />
      </a-form-item>
      <a-form-item label="父类">
        <a-input v-model:value="category.parent" />
      </a-form-item>
      <a-form-item label="顺序">
        <a-input v-model:value="category.sort" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";
import { message } from 'ant-design-vue';
import {Tool} from "../../../util/tool";


export default defineComponent({
  name:'AdminCategory',
  setup() {
    const param = ref();
    param.value = {};
    const categorys = ref();

    const loading = ref(false);

    const columns = [
      {
        title: '名称',
        key: 'name',
        dataIndex: 'name',
      },
      {
        title: '父类',
        key: 'parent',
        dataIndex: 'parent',
      },
      {
        title: '顺序',
        dataIndex: 'sort',
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' },
      },
    ];

    /**
     * 数据查询
     */
    const handleQuery = () => {
      loading.value = true;
      axios.get("/category/all", ).then((response)=>{
        loading.value = false;
        const data = response.data;
        if(data.success) {
          categorys.value = data.content;
        }else{
          message.error(data.message);
        }
      });
    };

    //----表单-----
    const category = ref({});
    const modalVisible = ref<boolean>(false);
    const showModal = () => {
      modalVisible.value = true;
    };
    const handleModalOk = (e: MouseEvent) => {
      axios.post("/category/save", category.value).then((response)=>{
        const data = response.data;
        if(data.success){
          console.log(e);
          modalVisible.value = false;

          //重新加载列表
          handleQuery();
        }else{
          message.error(data.message);
        }
      });
    };
    /**
     * add new note
     */
    const add = () => {
      modalVisible.value = true;
      category.value = {};
    }
    /**
     * 编辑
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      category.value = Tool.copy(record);
    }

    /**
     * delete the note
     */
    const handleDelete = (id: number) => {
      axios.delete("/category/delete/" + id).then((response)=>{
        const data = response.data;
        if(data.success){
          //重新加载列表
          handleQuery();
        }
      });
    }



    onMounted(()=>{
      handleQuery();
    });

    return {
      categorys,
      loading,
      columns,

      //编辑
      category,
      edit,
      add,
      modalVisible,
      showModal,
      handleModalOk,
      handleDelete,
      param,
      handleQuery,
    }
  }
});
</script>

<style scoped>
img {
  width: 50px;
  height: 50px;
}
</style>