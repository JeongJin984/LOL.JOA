import { useCallback, useState } from "react";

const useData = () => {
  const [data, setData] = useState('')

  const onChangeData = useCallback(e => {
    setData(e.target.value);
  }, [])
  return [data, onChangeData]
}

export default useData;