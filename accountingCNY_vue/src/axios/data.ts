import { getAll } from "./index";

const datas = [];
const indexData = (id: any) => {
  getAll(id).then((res) => {
    res.data.data = datas;
  });
};

export default datas;
